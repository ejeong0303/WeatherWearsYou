package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
        ArrayList<LinkedHashMap> weather = kmaClientVilageFcst.getVilageLandWeather();
        System.out.println(weather);
    }
}