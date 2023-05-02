package WeatherWearsYou.external;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KmaClientMidWeatherTest {

    KmaClientMidWeather kmaClientMidWeather;

    @BeforeEach
    public void init() {
        kmaClientMidWeather = new KmaClientMidWeather();
    }

    @Test
    void getMiddleLandWeather1() throws UnsupportedEncodingException {
        LinkedHashMap weather = kmaClientMidWeather.getMiddleLandWeather1();
        System.out.println(weather);
    }
}