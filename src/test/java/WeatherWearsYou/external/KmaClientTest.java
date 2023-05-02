package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KmaClientTest {

    KmaClientMidTemp kmaClientMidTemp;

    @BeforeEach
    public void init() {
        kmaClientMidTemp = new KmaClientMidTemp();
    }

    @Test
    void getMiddleLandWeather2() throws UnsupportedEncodingException {
        LinkedHashMap weather = kmaClientMidTemp.getMiddleLandWeather2();
        System.out.println(weather);
    }
}