//간혹 timeout error 발생
package WeatherWearsYou.external;

import WeatherWearsYou.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatGptController {

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
        var completion = CompletionRequest.defaultWith(message, style);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }


    @PostMapping(path = "/")
    public ResponseEntity<String> chat(@ModelAttribute FormInputDTO dto) {
        try {
            String city = dto.getCity(); // Get the city information from the frontend
            String gender = dto.getGender(); // Get the gender information from the frontend
            String targetDate = dto.getTargetDate(); // Get the target date information from the frontend
            LinkedHashMap<String, Object> weatherData = weatherController.getWeatherForDate(city, targetDate);
            String modifiedPrompt = String.format("%s,%s,%s,%s,%s,%s",
                    gender, targetDate,
                    weatherData.get("precipitationRate"),
                    weatherData.get("weather"),
                    weatherData.get("minTemp"),
                    weatherData.get("maxTemp"));
            String response;
            if (dto.getStyle() == null) {
                response = chatWithGpt3(modifiedPrompt.split(","), null);
            } else {
                response = chatWithGpt3(modifiedPrompt.split(","), dto.getStyle());
            }
            String[] categories = response.split("\\r?\\\n");

            LinkedHashMap<String, List<String>> results = new LinkedHashMap<>();
            String[] categoriesKeys = {"outer", "top", "bottom", "shoes"};

            for (int i = 2; i < categories.length; i++) {
                String[] items = categories[i].substring(categories[i].indexOf(":") + 1).trim().split(",\\s*");
                results.put(categoriesKeys[i - 2], new ArrayList<>(Arrays.asList(items)));
            }

            return ResponseEntity.status(HttpStatus.OK).body(jsonMapper.writeValueAsString(results));
        } catch (Exception e) {
            e.printStackTrace(); // Add this line to print the detailed error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in communication with OpenAI ChatGPT API.");
        }
    }
}
