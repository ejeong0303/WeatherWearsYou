package WeatherWearsYou.external;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KmaClientTest {

    KmaClient kmaClient;

    @BeforeEach
    public void init() {
        kmaClient = new KmaClient();
    }

    @Test
    void getMiddleLandWeather() throws UnsupportedEncodingException {
        LinkedHashMap weather = kmaClient.getMiddleLandWeather();
        System.out.println(weather);
    }
}