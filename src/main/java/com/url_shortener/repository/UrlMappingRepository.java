package com.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url_shortener.model.UrlMapping;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long>{
    boolean existsByUrlCurta(String urlCurta);
}
