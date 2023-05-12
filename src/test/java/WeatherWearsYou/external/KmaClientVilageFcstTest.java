package WeatherWearsYou.external;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

public class KmaClientVilageFcstTest {

    public static void main(String[] args) {
        KmaClientVilageFcst kmaClient = new KmaClientVilageFcst();
        String cityName = "gangwon";
        String targetDate = "20230514"; // Replace with your target date in "yyyyMMdd" format

        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            LinkedHashMap<String, Object> weatherData = kmaClient.getWeatherForSpecificDate(cityName, targetDate);
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
