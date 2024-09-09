package al.infnet.edu.br.reactive_client_webclient_TP3.services;

// Questão 7

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${meteomatics.api.username}")
    private String username;

    @Value("${meteomatics.api.password}")
    private String password;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .defaultHeader("Authorization", "Basic " + getBasicAuthHeader())
                .build();
    }

    private String getBasicAuthHeader() {
        String auth = username + ":" + password;
        return java.util.Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public Mono<String> getCurrentTemperature(String datetime, String location) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{datetime}/t_2m:C/{location}/html")
                        .build(datetime, location))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    System.err.println("Error fetching weather data: " + e.getMessage());
                    return Mono.error(new RuntimeException("Error fetching weather data", e));
                });
    }
}
