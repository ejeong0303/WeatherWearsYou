package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class KmaClientVilageFcst {
    public static void disableSslVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;

            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Integer[] getNxNyByCity(String cityName) {
        HashMap<String, Integer[]> cityToNxNy = new HashMap<>();
        cityToNxNy.put("sejong", new Integer[]{66, 103});
        cityToNxNy.put("chungbuk", new Integer[]{69, 107});
        cityToNxNy.put("chungnam", new Integer[]{55, 106});
        cityToNxNy.put("jeju", new Integer[]{52, 38});
        cityToNxNy.put("gyeongbuk", new Integer[]{89, 91});
        cityToNxNy.put("gyeongnam", new Integer[]{91, 77});
        cityToNxNy.put("jeonabuk", new Integer[]{63, 89});
        cityToNxNy.put("jeonnam", new Integer[]{52, 71});
        cityToNxNy.put("gangwon", new Integer[]{73, 134});
        cityToNxNy.put("gyeonggi", new Integer[]{60, 120});
        cityToNxNy.put("ulsan", new Integer[]{102, 84});
        cityToNxNy.put("busan", new Integer[]{98, 76});
        cityToNxNy.put("daegu", new Integer[]{89, 90});
        cityToNxNy.put("daejeon", new Integer[]{67, 100});
        cityToNxNy.put("incheon", new Integer[]{55, 124});
        cityToNxNy.put("seoul", new Integer[]{60, 127});
        cityToNxNy.put("gwangju", new Integer[]{58, 74});

        return cityToNxNy.getOrDefault(cityName, new Integer[]{60, 127}); // Default to 서울특별시 if city not found
    }
    private ArrayList<LinkedHashMap> getVilageLandWeather(String cityName) throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        WebClient webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Integer[] nxNyValues = getNxNyByCity(cityName);
        disableSslVerification();
        HashMap result = webClient.get()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("1360000/VilageFcstInfoService_2.0/getVilageFcst")
                        .queryParam("serviceKey",
                                "rm1FBKWt03J9o1QIzMbcQ8LcKOuxPDgITzdTd9NoXdslz1L8FF916FbILfYsbJmqkr0bJJHrRj3tHQe1DL/ecg==")
                        .queryParam("pageNo", "1")
                        .queryParam("numOfRows", "1000")
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", sdf.format(date))
                        .queryParam("base_time", "0500")
                        .queryParam("nx", nxNyValues[0])
                        .queryParam("ny", nxNyValues[1])

                        .build()
                        .toUriString())
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        LinkedHashMap response = (LinkedHashMap) result.get("response");
        if (response == null) {
            throw new RuntimeException("Response object is missing in the API response");
        }

        LinkedHashMap body = (LinkedHashMap) response.get("body");
        if (body == null) {
            throw new RuntimeException("Body object is missing in the API response");
        }

        LinkedHashMap items = (LinkedHashMap) body.get("items");
        if (items == null) {
            throw new RuntimeException("Body object is missing in the API response");
        }

        ArrayList<LinkedHashMap> itemList = (ArrayList<LinkedHashMap>) items.get("item"); // Updated itemList type
        if (itemList == null || itemList.isEmpty()) {
            throw new RuntimeException("Item list is missing or empty in the API response");
        }

        return itemList; // Return the entire itemList
    }
    private String getWeatherStatus(int fcstValue, int pty) {
        if (pty != 0) {
            return "rainy";
        }
        switch (fcstValue) {
            case 1:
                return "sunny";
            case 3:
                return "cloudy";
            case 4:
                return "blurry";
            default:
                return "";
        }
    }
    LinkedHashMap<String, Object> getWeatherForSpecificDate(String cityName, String targetDate) throws UnsupportedEncodingException, ParseException {
        ArrayList<LinkedHashMap> itemList = getVilageLandWeather(cityName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // Calculate the difference between the target date and today's date
        Date today = new Date();
        Date target = sdf.parse(targetDate);
        long diff = (target.getTime() - today.getTime()) / (24 * 60 * 60 * 1000) + 1;

        // Filter itemList to only have items matching the targetDate
        ArrayList<LinkedHashMap> filteredItems = itemList.stream()
                .filter(item -> targetDate.equals(item.get("fcstDate")))
                .collect(Collectors.toCollection(ArrayList::new));
        int pty = filteredItems.stream().filter(item -> "PTY".equals(item.get("category"))).mapToInt(item -> (int)Double.parseDouble((String)item.get("fcstValue"))).findFirst().orElse(0);

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("minTemp", filteredItems.stream().filter(item -> "TMN".equals(item.get("category"))).mapToInt(item -> (int)Double.parseDouble((String)item.get("fcstValue"))).findFirst().orElse(0));
        result.put("maxTemp", filteredItems.stream().filter(item -> "TMX".equals(item.get("category"))).mapToInt(item -> (int)Double.parseDouble((String)item.get("fcstValue"))).findFirst().orElse(0));
        result.put("precipitationRate", filteredItems.stream().filter(item -> "POP".equals(item.get("category"))).mapToInt(item -> (int)Double.parseDouble((String)item.get("fcstValue"))).findFirst().orElse(0));
  //      result.put("weather", filteredItems.stream().filter(item -> "SKY".equals(item.get("category"))).mapToInt(item -> (int)Double.parseDouble((String)item.get("fcstValue"))).findFirst().orElse(0));
        int sky = filteredItems.stream().filter(item -> "SKY".equals(item.get("category"))).mapToInt(item -> (int)Double.parseDouble((String)item.get("fcstValue"))).findFirst().orElse(0);
        result.put("weather", getWeatherStatus(sky, pty));

        return result;
    }

}
