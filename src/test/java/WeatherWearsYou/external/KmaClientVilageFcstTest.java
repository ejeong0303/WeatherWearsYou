package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KmaClientVilageFcstTest {

    KmaClientVilageFcst kmaClientVilageFcst;

    @BeforeEach
    public void init() {
        kmaClientVilageFcst = new KmaClientVilageFcst();
    }

    @Test
    void getVilageLandWeather() throws UnsupportedEncodingException {
        LinkedHashMap weather = kmaClientVilageFcst.getVilageLandWeather();
        System.out.println(weather);
    }
}