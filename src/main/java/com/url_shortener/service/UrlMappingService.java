package com.url_shortener.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.url_shortener.dto.url.UrlRequestDto;
import com.url_shortener.dto.url.UrlResponseDto;
import com.url_shortener.model.UrlMapping;
import com.url_shortener.repository.UrlMappingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository repository;

    public UrlResponseDto gerarUrlMapping(UrlRequestDto dto) {

        String urlCurta;
        do {
            urlCurta = gerarUrl();
        } while (repository.existsByUrlCurta(urlCurta));

        UrlMapping url = new UrlMapping();

        url.setUrlLonga(dto.getUrlLonga());
        url.setUrlCurta(urlCurta);
        url.setQntCliques(0);

        UrlMapping urlSalva = repository.save(url);
        
        UrlResponseDto response = new UrlResponseDto();
        response.setUrlCurta(urlSalva.getUrlCurta());

        return response;
    }

    public String redirecionar(String urlCurta) {
        UrlMapping url = repository.findByUrlCurta(urlCurta).orElseThrow(() -> new IllegalArgumentException("Url Inválida"));
        return url.getUrlLonga();
    }

    public String gerarUrl() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
