package ds.weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
@PropertySource(value = "classpath:locations.properties")
@ConfigurationProperties(prefix="data")
public class Locations  {
    private static final Logger log = LoggerFactory.getLogger(Locations.class);

    List<Location> locations = new ArrayList<>();

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Bean
    public List<Location> locationsList() {
        return locations;
    }

}
