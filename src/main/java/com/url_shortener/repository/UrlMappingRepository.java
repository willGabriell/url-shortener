package com.url_shortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url_shortener.model.UrlMapping;


public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long>{
    boolean existsByUrlCurta(String urlCurta);
    Optional<UrlMapping> findByUrlCurta(String urlCurta);
}
