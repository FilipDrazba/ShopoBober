package pl.edu.pb.wi.reportservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("report")
public class ReportController {

    @GetMapping("small")
    public Mono<String> ping() {
        return Mono.just("pong");
    }

    @GetMapping("big")
    public Mono<String> generateReport() {
        return Mono
                .delay(Duration.ofSeconds(15))
                .then(Mono.just("Report content"));
    }
}
