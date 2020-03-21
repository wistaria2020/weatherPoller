package ds.weather;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureTest {

    @Test
    void setFahrenheit_freezing() {
        Temperature t = new Temperature();
        t.setFahrenheit(32d);
        assertEquals( 0d, t.getValue());
    }

    @Test
    void setFahrenheit_boiling() {
        Temperature t = new Temperature();
        t.setFahrenheit(212d);
        assertEquals( 100d, t.getValue());
    }

    @Test
    void getFormatted() {
        Temperature t = new Temperature(-18.21);
        assertEquals("-18,2", t.getFormatted(1));

    }
}