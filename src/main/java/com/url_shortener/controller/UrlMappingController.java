package com.url_shortener.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url_shortener.dto.url.UrlRequestDto;
import com.url_shortener.dto.url.UrlResponseDto;
import com.url_shortener.service.UrlMappingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UrlMappingController {

    public final UrlMappingService service;

    @PostMapping
    public ResponseEntity<UrlResponseDto> cadastrarUrl(@RequestBody @Valid UrlRequestDto dto) {
        UrlResponseDto url = service.gerarUrlMapping(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Void> redirecionar(@PathVariable String codigo) {
        String url = service.redirecionar(codigo);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

}
