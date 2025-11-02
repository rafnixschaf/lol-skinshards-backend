package tech.sascha.skinshard_virtualizer;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.sascha.skinshard_virtualizer.service.LockFileService;

import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;


@Component
public class LcuClient {

    @Autowired
    private LockFileService lockFileService;

    private WebClient buildClient(LockFileService.LcuAuth auth) {
        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> {
                    try {
                        sslContextSpec.sslContext(SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build());

                    } catch (SSLException e) {
                        throw new RuntimeException(e);
                    }
                });

        String basic = Base64.getEncoder().encodeToString((auth.username() + ":" + auth.password()).getBytes());

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(auth.protocol() + "://127.0.0.1:" + auth.port())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + basic)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<Map> getPlayerLoot() {
        var auth = lockFileService.readLockFile();
        var client = buildClient(auth);

        return client.get()
                .uri("/lol-loot/v1/player-loot")
                .retrieve()
                .bodyToFlux(Map.class)
                .collectList()
                .block();
    }
}
