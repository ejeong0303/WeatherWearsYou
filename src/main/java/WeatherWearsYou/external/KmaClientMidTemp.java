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

public class KmaClientMidTemp {

    private String getRegIdByCity(String cityName) {
        HashMap<String, String> cityToRegId = new HashMap<>();
        cityToRegId.put("Sejong", "11C20404");
        cityToRegId.put("Chungcheongbukdo", "11C10301");
        cityToRegId.put("Chungcheongnamdo", "11C20104");
        cityToRegId.put("Jejudo", "11G00201");
        cityToRegId.put("Gyeongsangbukdo", "11H10501");
        cityToRegId.put("Gyeongsangnamdo", "11H20301");
        cityToRegId.put("Jeollabukdo", "11F10201");
        cityToRegId.put("Jeollanamdo", "21F20804");
        cityToRegId.put("Gangwondo", "11D10301");
        cityToRegId.put("Gyeonggido", "11B20601");
        cityToRegId.put("Ulsan", "11H20101");
        cityToRegId.put("Busan", "11H20201");
        cityToRegId.put("Daegu", "11H10701");
        cityToRegId.put("Daejeon", "11C20401");
        cityToRegId.put("Incheon", "11B20201");
        cityToRegId.put("Seoul", "11B10101");
        cityToRegId.put("Gwangju", "11B20702");

        return cityToRegId.getOrDefault(cityName, "11B10101"); // Default to 서울특별시 if city not found
    }

    private LinkedHashMap getMiddleLandTempData(String cityName) throws UnsupportedEncodingException {
        String regId = getRegIdByCity(cityName);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        WebClient webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        HashMap result = webClient.get()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/1360000/MidFcstInfoService/getMidTa")
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

        LinkedHashMap itemMap = (LinkedHashMap) ((ArrayList)((LinkedHashMap)((LinkedHashMap)((LinkedHashMap) result.get("response")).get("body")).get("items")).get("item")).get(0);

        return itemMap;
    }
    public LinkedHashMap<String, Integer> getWeatherForSpecificDate(String cityName, String targetDate) throws UnsupportedEncodingException, ParseException {
        // Get the weather data
        LinkedHashMap<String, Object> weatherData = getMiddleLandTempData(cityName);

        // Calculate the difference between the target date and today's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date today = new Date();
        Date target = sdf.parse(targetDate);
        long diff = (target.getTime() - today.getTime()) / (24 * 60 * 60 * 1000) + 1;

        // Check if the date difference is within the range of 3 to 10 days
        if (diff <= 2 || diff >= 11) {
            throw new IllegalArgumentException("Target date must be between 3 to 10 days from today.");
        }

        // Get the weather and precipitation rate for the target date
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        result.put("minTemp", Integer.parseInt(String.valueOf(weatherData.get("taMin" + diff))));
        result.put("maxTemp", Integer.parseInt(String.valueOf(weatherData.get("taMax" + diff))));
        return result;
    }
}