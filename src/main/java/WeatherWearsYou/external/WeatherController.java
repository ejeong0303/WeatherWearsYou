//달력 화면에서 날씨정보 제공목적
package WeatherWearsYou.external;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class WeatherController {

    private final KmaClientMidTemp kmaClientMidTemp;
    private final KmaClientMidWeather kmaClientMidWeather;
    private final KmaClientVilageFcst kmaClientVilageFcst;

    public WeatherController() {
        kmaClientMidTemp = new KmaClientMidTemp();
        kmaClientMidWeather = new KmaClientMidWeather();
        kmaClientVilageFcst = new KmaClientVilageFcst();
    }

    @GetMapping("/weather/{cityName}")
    public LinkedHashMap<String, Object> getWeatherForDate(@PathVariable String cityName,
                                                           @RequestParam String targetDate)
            throws UnsupportedEncodingException, ParseException {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();
        Date target = sdf.parse(targetDate);
        long diff = (target.getTime() - today.getTime()) / (24 * 60 * 60 * 1000) + 1;

        if (diff >= 0 && diff <= 2) {
            result = kmaClientVilageFcst.getWeatherForSpecificDate(cityName, targetDate);
        } else if (diff >= 3 && diff <= 10) {
            LinkedHashMap<String, Integer> tempResult = kmaClientMidTemp.getWeatherForSpecificDate(cityName, targetDate);
            LinkedHashMap<String, String> weatherResult = kmaClientMidWeather.getWeatherForSpecificDate(cityName, targetDate);

            result.put("minTemp", tempResult.get("minTemp"));
            result.put("maxTemp", tempResult.get("maxTemp"));
            result.put("weather", weatherResult.get("weather"));
            result.put("precipitationRate", weatherResult.get("PrecipitationRate"));
        } else {
            throw new IllegalArgumentException("Target date must be within 10 days from today.");
        }

        return result;
    }
}
