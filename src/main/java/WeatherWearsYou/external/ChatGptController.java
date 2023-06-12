//간혹 timeout error 발생
package WeatherWearsYou.external;

import WeatherWearsYou.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import WeatherWearsYou.external.OpenAiApiClient.OpenAiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class ChatGptController {
    private static final int MAX_RETRIES = 10;

    @Autowired private ObjectMapper jsonMapper;
    @Autowired private OpenAiApiClient client;
    @Autowired private WeatherController weatherController;
    @Autowired private ItemService itemService;

    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public void setClient(OpenAiApiClient client) {
        this.client = client;
    }

    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    private String chatWithGpt3(String[] message, String style) throws Exception {
        for(int i = 0; i < MAX_RETRIES; i++) {
            try {
                var completion = CompletionRequest.defaultWith(message, style);
                var postBodyJson = jsonMapper.writeValueAsString(completion);
                var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
                var completionResponse = jsonMapper.readValue(new String(responseBody, StandardCharsets.UTF_8), CompletionResponse.class);
                return completionResponse.firstAnswer().orElseThrow();
            } catch(Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                if (i == MAX_RETRIES - 1) {
                    throw e; // re-throw the exception if on last retry
                }
            }
        }
        throw new Exception("Failed to get a response from the API after " + MAX_RETRIES + " attempts");
    }

    @PostMapping(path = "/")
    public ResponseEntity<byte[]> chat(@ModelAttribute FormInputDTO dto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));

        try {
            String city = dto.getCity(); // Get the city information from the frontend
            String gender = dto.getGender(); // Get the gender information from the frontend
            String targetDate = dto.getTargetDate(); // Get the target date information from the frontend
            String modifiedPrompt;
            String maxTemp = null;
            if(dto.getWeather() == null) {
                LinkedHashMap<String, Object> weatherData = weatherController.getWeatherForDate(city, targetDate);
                maxTemp = Integer.toString((Integer) weatherData.get("maxTemp"));
                modifiedPrompt = String.format("%s,%s,%s,%s,%s,%s",
                        gender, targetDate,
                        weatherData.get("precipitationRate"),
                        weatherData.get("weather"),
                        weatherData.get("minTemp"),
                        maxTemp);
            } else {
                maxTemp = dto.getMaxTemp();
                modifiedPrompt = String.format("%s,%s,%s,%s,%s,%s",
                        gender, targetDate,
                        dto.getPrecipitationRate(),
                        dto.getWeather(),
                        dto.getMinTemp(),
                        maxTemp);
            }
            String response;
            if (dto.getStyle() == null) {
                response = chatWithGpt3(modifiedPrompt.split(","), null);
            } else {
                response = chatWithGpt3(modifiedPrompt.split(","), dto.getStyle());
            }
            String[] categories = response.split("\\r?\\\n");

            categories = Arrays.copyOfRange(categories, 2, categories.length);

            LinkedHashMap<String, List<String>> results = new LinkedHashMap<>();
            String[] categoriesKeys = {"Top", "Bottom", "Outer", "Shoes"};

            for (int i = 0; i < categories.length; i++) {
                String[] items = categories[i].substring(categories[i].indexOf(":") + 2).trim().split("#");
                List<String> translatedItems = new ArrayList<>();
                for (String item : items) {
                    String translatedItem = TranslationUtil.translateEngToKor(item);

                    if (!translatedItem.matches("[a-zA-Z ]+") || item != "") {
                        translatedItems.add(translatedItem);
                    }
                }
                results.put(categoriesKeys[i], translatedItems);
            }

            // Remove 'Outer' if max temperature is >= 10
            if (maxTemp != null && Double.parseDouble(maxTemp) >= 10) {
                results.remove("Outer");
            }

            System.out.println(results);

            String jsonResponse = jsonMapper.writeValueAsString(results);
            byte[] jsonResponseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
            return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(jsonResponseBytes);
        } catch (Exception e) {
            e.printStackTrace(); // Add this line to print the detailed error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).body(new byte[0]);
        }
    }
}

