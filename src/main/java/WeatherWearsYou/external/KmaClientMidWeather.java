package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class KmaClientMidWeather {
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
    private String getRegIdByCity(String cityName) {
        HashMap<String, String> cityToRegId = new HashMap<>();
        cityToRegId.put("sejong", "11C20000");
        cityToRegId.put("chungbuk", "11C10000");
        cityToRegId.put("chungnam", "11C20000");
        cityToRegId.put("jeju", "11G00000");
        cityToRegId.put("gyeongbuk", "11H10000");
        cityToRegId.put("gyeongnam", "11H20000");
        cityToRegId.put("jeonbuk", "11F10000");
        cityToRegId.put("jeonnam", "11F20000");
        cityToRegId.put("gangwon", "11D10000");
        cityToRegId.put("gyeonggi", "11B00000");
        cityToRegId.put("ulsan", "11H20000");
        cityToRegId.put("busan", "11H20000");
        cityToRegId.put("daegu", "11H10000");
        cityToRegId.put("daejeon", "11C20000");
        cityToRegId.put("incheon", "11B00000");
        cityToRegId.put("seoul", "11B00000");
        cityToRegId.put("gwangju", "11F20000");

        return cityToRegId.getOrDefault(cityName, "11B00000"); // Default to 서울특별시 if city not found
    }
    private LinkedHashMap<String, Object> getMiddleLandWeatherData(String cityName) throws UnsupportedEncodingException {
        String regId = getRegIdByCity(cityName);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        disableSslVerification();
        WebClient webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        HashMap result = webClient.get()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/1360000/MidFcstInfoService/getMidLandFcst")
                        .queryParam("serviceKey",
                                "rm1FBKWt03J9o1QIzMbcQ8LcKOuxPDgITzdTd9NoXdslz1L8FF916FbILfYsbJmqkr0bJJHrRj3tHQe1DL/ecg==")
                        .queryParam("regId", regId)
                        .queryParam("tmFc", sdf.format(date) + "0600")
                        .queryParam("pageNo", "1")
                        .queryParam("numOfRows", "10")
                        .queryParam("dataType", "JSON")
                        .build()
                        .toUriString())
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        return (LinkedHashMap) ((ArrayList) ((LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) result.get("response")).get("body")).get("items")).get("item")).get(0);
    }
    public LinkedHashMap<String, String> getWeatherForSpecificDate(String cityName, String targetDate) throws UnsupportedEncodingException, ParseException {
        // Get the weather data
        LinkedHashMap<String, Object> weatherData = getMiddleLandWeatherData(cityName);

        // Calculate the difference between the target date and today's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();
        Date target = sdf.parse(targetDate);
        long diff = (target.getTime() - today.getTime()) / (24 * 60 * 60 * 1000) + 1;

        // Check if the date difference is within the range of 3 to 10 days
        if (diff <= 2 || diff >= 11) {
            throw new IllegalArgumentException("Target date must be between 3 to 10 days from today.");
        }

        // Get the weather and precipitation rate for the target date
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        String weatherStatus = "null";
        if (diff <= 7) weatherStatus = (String) weatherData.get("wf" + diff + "Am");
        else if(diff >= 8) weatherStatus = (String) weatherData.get("wf" + diff);

        switch (weatherStatus) {
            case "맑음":
                weatherStatus = "sunny";
                break;
            case "구름많음":
                weatherStatus = "cloudy";
                break;
            case "흐리고 비":
                weatherStatus = "rainy";
                break;
            case "흐림":
                weatherStatus = "blurry";
                break;
            default:
                break;
        }
        result.put("weather", weatherStatus);
        int PrecipitationRate = 0;
        if (diff <= 7) {
            double precipitationRateAm = Double.parseDouble(String.valueOf(weatherData.get("rnSt" + diff + "Am")));
            double precipitationRatePm = Double.parseDouble(String.valueOf(weatherData.get("rnSt" + diff + "Pm")));

            // Calculate the mean precipitation rate and round it to the nearest integer
            PrecipitationRate = (int) Math.round((precipitationRateAm + precipitationRatePm) / 2);
        }
        else if(diff >= 8){
            PrecipitationRate = (int)Double.parseDouble(String.valueOf(weatherData.get("rnSt" + diff)));
        }
        result.put("PrecipitationRate", String.valueOf(PrecipitationRate));

        return result;
    }

}
