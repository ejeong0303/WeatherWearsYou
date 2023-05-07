package WeatherWearsYou.external;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.LinkedHashMap;

public class KmaClientMidTempTest {
    public static void main(String[] args) {
        KmaClientMidTemp client = new KmaClientMidTemp();

        // Set a target date within the range of 3 to 10 days from today
        String targetDate = "2023.05.10";

        // Set a city name
        String cityName = "Chungcheongnamdo";

        try {
            // Set the output encoding to UTF-8
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));

            LinkedHashMap<String, Integer> temperatureInfo = client.getWeatherForSpecificDate(cityName, targetDate);
            System.out.println("Temperature information for " + cityName + " on " + targetDate + ":");
            System.out.println("Minimum Temperature: " + temperatureInfo.get("minTemp"));
            System.out.println("Maximum Temperature: " + temperatureInfo.get("maxTemp"));
        } catch (UnsupportedEncodingException | ParseException e) {
            e.printStackTrace();
        }
    }
}
