package ds.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@SpringBootApplication
@ConfigurationProperties(prefix="app")
@EnableScheduling
public class WeatherApplication {
    private static final Logger log = LoggerFactory.getLogger(WeatherApplication.class);

    @Scheduled(fixedRate = 60000)
    public void scheduleFixedRateTask() {
        LocalDateTime startTime = LocalDateTime.now();

        List<Location> abnormal =
        locationsList.stream()
                .filter(l -> l.getNextPolling()
                        .isBefore(startTime))
                .peek(l -> {l.setTemperature(weatherPoller.getTemperature(l));
                l.setNextPolling(startTime.plusMinutes(l.getPollingIntervalMinutes()));})
                .peek(l -> log.info("Temperature in " + l.getName() + " is "
                        + l.getTemperature().getFormatted(1)))
                .filter(Location::isTemperatureAbnormal).
                collect(Collectors.toList());

        sendNotification(abnormal, startTime);
    }

    private void sendNotification(List<Location> abnormal, LocalDateTime time){
        if (!CollectionUtils.isEmpty(abnormal)) {
            String subject = "Abnormal temperature in " +
                    abnormal.stream()
                            .limit(5)
                            .map(Location::getName)
                            .collect(joining(", ")) +
                    (abnormal.size() > 5 ? "..." : ".");
            String text = "Temperatures registered at " +
                    time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                    " (" +
                    TimeZone.getDefault().getID() +
                    " time)\n" +
                    abnormal.stream()
                            .map(l -> l.getName() + "\t" + l.getTemperature().getFormatted(1) + "°C")
                            .collect(joining("\n"));
            emailNotifier.sendSimpleMessage(subject, text);
        }
    }

    public WeatherApplication(List<Location> locationsList,
                              WeatherPoller weatherPoller,
                              EmailNotifier emailNotifier) {
        this.locationsList = locationsList;
        this.weatherPoller = weatherPoller;
        this.emailNotifier = emailNotifier;
    }

    private List<Location> locationsList;

    private WeatherPoller weatherPoller;

    private EmailNotifier emailNotifier;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

}
