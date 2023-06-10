package WeatherWearsYou.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

public class ChatGptControllerTest {

    public static void main(String[] args) {

        // Manually create instances of required dependencies
        ObjectMapper jsonMapper = new ObjectMapper();
        OpenAiApiClient client = new OpenAiApiClient(); // You may need to set the API key manually

        client.setOpenaiApiKey("current key"); // Replace with your actual API key




        WeatherController weatherController = new WeatherController(); // You may need to set the API key manually

        // Create the ChatGptController instance and set dependencies
        ChatGptController chatGptController = new ChatGptController();
        chatGptController.setJsonMapper(jsonMapper);
        chatGptController.setClient(client);
        //chatGptController.setWeatherController(weatherController);

        // Call the chat method with a FormInputDTO containing the city, gender, target date, and style
        FormInputDTO dto = new FormInputDTO();
        dto.setCity("gangwon");
        dto.setGender("female");
        dto.setTargetDate("20240113");
        dto.setStyle("girlish lovely"); // Set the style value
        dto.setMaxTemp("-15");
        dto.setMinTemp("-30");
        dto.setPrecipitationRate("30");
        dto.setWeather("blurry");
        ResponseEntity<byte[]> response = chatGptController.chat(dto);

        // Print the response
        System.out.println("Response:");
        System.out.println(new String(response.getBody(), StandardCharsets.UTF_8));
    }
}
