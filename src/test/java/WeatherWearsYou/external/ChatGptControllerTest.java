package WeatherWearsYou.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

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

        // Call the chat method with a FormInputDTO containing the city, gender, target date, and style
        FormInputDTO dto = new FormInputDTO();
        dto.setCity("Seoul");
        dto.setGender("male");
        dto.setTargetDate("20230514");
        dto.setStyle("댄디 남친룩"); // Set the style value
        ResponseEntity<String> response = chatGptController.chat(dto);

        // Print the response
        System.out.println("Response:");
        System.out.println(response.getBody());
    }
}
