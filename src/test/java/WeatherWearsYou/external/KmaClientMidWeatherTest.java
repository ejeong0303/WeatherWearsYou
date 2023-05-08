package WeatherWearsYou.external;

import java.text.ParseException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class KmaClientMidWeatherTest {
    public static void main(String[] args) {
        KmaClientMidWeather client = new KmaClientMidWeather();
        String cityName = "Gangwondo";
        // Set a target date within the range of 3 to 10 days from today
        String targetDate = "20230514";

        try {
            // Set the character encoding of the output stream to UTF-8
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

            LinkedHashMap<String, String> weatherInfo = client.getWeatherForSpecificDate(cityName,targetDate);
            System.out.println("Weather information for " + cityName + " in " + targetDate + ":");
            System.out.println("Weather: " + weatherInfo.get("weather"));
            System.out.println("Precipitation Rate: " + weatherInfo.get("PrecipitationRate"));
        } catch (UnsupportedEncodingException | ParseException e) {
            e.printStackTrace();
        }
    }
}
