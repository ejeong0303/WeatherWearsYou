package WeatherWearsYou.external;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

@RestController
public class WeatherController {
    private final KmaClientVilageFcst vilageFcst = new KmaClientVilageFcst();
    private final KmaClientMidWeather midWeather = new KmaClientMidWeather();
    private final KmaClientMidTemp midTemp = new KmaClientMidTemp();

    @GetMapping("/weather")
    public LinkedHashMap<String, Object> getWeather(@RequestParam("city") String cityName, @RequestParam("date") String targetDate) throws UnsupportedEncodingException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date today = new Date();
        Date target = sdf.parse(targetDate);
        long diff = (target.getTime() - today.getTime()) / (24 * 60 * 60 * 1000) + 1;

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        if (diff >= 0 && diff <= 2) {
            result = vilageFcst.getWeatherForSpecificDate(cityName, targetDate);
        } else if (diff >= 3 && diff <= 10) {
            LinkedHashMap<String, Integer> tempResult = midTemp.getWeatherForSpecificDate(cityName, targetDate);
            LinkedHashMap<String, String> weatherResult = midWeather.getWeatherForSpecificDate(cityName, targetDate);
            result.putAll(tempResult);
            result.putAll(weatherResult);
        } else {
            throw new IllegalArgumentException("Target date must be between 0 to 10 days from today.");
        }

        return result;
    }
}
