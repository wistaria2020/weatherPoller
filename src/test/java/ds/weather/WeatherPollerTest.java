package ds.weather;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherPollerTest {


    @Test
    void getTemperature() throws Exception{
        String json = new String(Files.readAllBytes
                (Paths.get(ClassLoader.getSystemResource("test.json").toURI())));

        assertEquals(52.14d, WeatherPoller.getTemperature(json));
    }
}