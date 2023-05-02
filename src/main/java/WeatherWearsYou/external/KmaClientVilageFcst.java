package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class KmaClientVilageFcst {

    public ArrayList<LinkedHashMap> getVilageLandWeather() throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        WebClient webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

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
                        .queryParam("nx", "55")
                        .queryParam("ny", "127")

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
}
