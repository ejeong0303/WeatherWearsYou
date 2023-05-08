package WeatherWearsYou.external;

import org.junit.Test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class WeatherControllerTest {

    public static void main(String[] args) {
        WeatherController weatherController = new WeatherController();
        String cityName = "Gangwondo";
        String targetDate = "20230514"; // Replace with your target date in "yyyy.MM.dd" format

        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            LinkedHashMap<String, Object> weatherData = weatherController.getWeatherForDate(cityName, targetDate);
            System.out.println("Weather data for " + cityName + " in " + targetDate + ":");
            System.out.println("Minimum Temperature: " + weatherData.get("minTemp"));
            System.out.println("Maximum Temperature: " + weatherData.get("maxTemp"));
            System.out.println("Precipitation Rate: " + weatherData.get("precipitationRate"));
            System.out.println("Weather: " + weatherData.get("weather"));
        } catch (UnsupportedEncodingException | ParseException e) {
            e.printStackTrace();
        }
    }
}
