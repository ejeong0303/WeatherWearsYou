package WeatherWearsYou.external;

import WeatherWearsYou.external.*;
import WeatherWearsYou.*;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatGptControllerTest {

    public static void main(String[] args) {

        // Manually create instances of required dependencies
        ObjectMapper jsonMapper = new ObjectMapper();
        OpenAiApiClient client = new OpenAiApiClient(); // You may need to set the API key manually
        client.setOpenaiApiKey("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"); // Replace with your actual API key

        WeatherController weatherController = new WeatherController(); // You may need to set the API key manually

        // Create the ChatGptController instance and set dependencies
        ChatGptController chatGptController = new ChatGptController();
        chatGptController.setJsonMapper(jsonMapper);
        chatGptController.setClient(client);
        chatGptController.setWeatherController(weatherController);

        // Call the chat method with a FormInputDTO containing the prompt
        FormInputDTO dto = new FormInputDTO("female,20230508");
        ResponseEntity<String> response = chatGptController.chat(dto);

        // Print the response
        System.out.println("Response:");
        System.out.println(response.getBody());
    }
}
