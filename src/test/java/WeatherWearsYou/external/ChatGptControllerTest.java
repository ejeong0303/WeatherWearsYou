package WeatherWearsYou.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

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
        dto.setCity("gangwon");
        dto.setGender("male");
        dto.setTargetDate("20230604");
        dto.setStyle("Sporty"); // Set the style value
        ResponseEntity<byte[]> response = chatGptController.chat(dto);

        // Print the response
        System.out.println("Response:");
        System.out.println(new String(response.getBody(), StandardCharsets.UTF_8));
    }
}
