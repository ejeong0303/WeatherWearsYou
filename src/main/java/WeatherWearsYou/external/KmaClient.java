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

public class KmaClient {

    public LinkedHashMap getMiddleLandWeather() throws UnsupportedEncodingException {
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
                        .queryParam("regId", "11B10101")
                        .queryParam("tmFc", "202305010600")
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
}
