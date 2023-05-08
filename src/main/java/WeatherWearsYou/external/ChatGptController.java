package WeatherWearsYou.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import WeatherWearsYou.FormInputDTO;
import WeatherWearsYou.OpenAiApiClient;
import WeatherWearsYou.OpenAiApiClient.OpenAiService;

import java.util.LinkedHashMap;

@RestController
public class ChatGptController {

    @Autowired private ObjectMapper jsonMapper;
    @Autowired private OpenAiApiClient client;
    @Autowired private WeatherController weatherController;

    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public void setClient(OpenAiApiClient client) {
        this.client = client;
    }

    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    private String chatWithGpt3(String[] message) throws Exception {
        var completion = CompletionRequest.defaultWith(message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> chat(@ModelAttribute FormInputDTO dto) {
        try {
            String[] words = dto.prompt().split(",");
            LinkedHashMap<String, Object> weatherData = weatherController.getWeatherForDate("Seoul", words[1]);
            String modifiedPrompt = String.format("%s,%s,%s,%s,%s,%s",
                    words[0], words[1],
                    weatherData.get("precipitationRate"),
                    weatherData.get("weather"),
                    weatherData.get("minTemp"),
                    weatherData.get("maxTemp"));
            String response = chatWithGpt3(modifiedPrompt.split(","));
            String[] categories = response.split("\\r?\\\n");

            return ResponseEntity.status(HttpStatus.OK).body(jsonMapper.writeValueAsString(new LinkedHashMap<String, String>() {{
                put("outer", categories[2].substring(categories[0].indexOf(":") + 1).trim());
                put("top", categories[3].substring(categories[1].indexOf(":") + 1).trim());
                put("bottom", categories[4].substring(categories[2].indexOf(":") + 1).trim());
                put("shoes", categories[5].substring(categories[3].indexOf(":") + 1).trim());
            }}));
        } catch (Exception e) {
            e.printStackTrace(); // Add this line to print the detailed error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in communication with OpenAI ChatGPT API.");
        }
    }
}
