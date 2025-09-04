package com.pjrminis.diecast.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class WebFluxController {

    @GetMapping("/webflux")
    public Mono<String> helloWebFlux() {
        return Mono.just("Olá, WebFlux!");
    }
}