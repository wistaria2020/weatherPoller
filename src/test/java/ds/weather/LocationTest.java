package ds.weather;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void isTemperatureAbnormal_noAlerts() {
        Location l = new Location("Helsinki", 60.1699, 24.9384);
        l.setTemperature(new Temperature(15d));
        assertFalse(l.isTemperatureAbnormal());
    }

    @Test
    void isTemperatureAbnormal_alerts() {
        Location l = new Location("Helsinki", 60.1699, 24.9384);
        l.setTemperatureAlertLow(-25d);
        l.setTemperatureAlertHigh(25d);
        l.setTemperature(new Temperature(30d));
        assertTrue(l.isTemperatureAbnormal());
    }
}