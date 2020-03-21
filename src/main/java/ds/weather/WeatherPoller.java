package ds.weather;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Configuration
@ConfigurationProperties(prefix="poller")
public class WeatherPoller {

    Temperature getTemperature(Location l) {
        Temperature t = new Temperature();
        try {
            t.setFahrenheit(getTemperature(getJsonData(getUri(l))));
        } catch (Exception e) {
            log.warn("Error getting temperature for " + l.getName(), e);
        }
        return t;

    }

    //parsing json
    static double getTemperature(String json) {
        return new Gson().fromJson(json,
                JsonObject.class).get("currently").getAsJsonObject().get("temperature").getAsNumber().doubleValue();
    }

    //getting weather data from webservice
    String getJsonData(String uri) {
        return restTemplate.getForObject(uri, String.class);
    }

    //constructing webservice uri
    String getUri(Location l) {
        try {
            return new URIBuilder(url)
                    .setPathSegments(   "forecast",
                                        secretKey,
                                        "" + l.getLatitude() + "," + l.getLongitude())
                    .toString();
        } catch (URISyntaxException e) {
            log.warn("wrong URI syntax", e);
            return url;
        }
    }


    public RestTemplate restTemplate;

    public WeatherPoller(RestTemplate restTemplate) {
        this.restTemplate =restTemplate;
    }

    private static final Logger log = LoggerFactory.getLogger(WeatherPoller.class);

    String url;
    String secretKey;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
