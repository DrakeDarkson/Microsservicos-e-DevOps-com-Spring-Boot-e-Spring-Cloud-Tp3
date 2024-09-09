package al.infnet.edu.br.reactive_client_webclient_TP3.controllers;

import al.infnet.edu.br.reactive_client_webclient_TP3.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Mono<String> getWeather(@RequestParam String datetime, @RequestParam String location) {
        return weatherService.getCurrentTemperature(datetime, location);
    }
}
