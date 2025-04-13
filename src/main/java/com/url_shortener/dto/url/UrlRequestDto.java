package com.url_shortener.dto.url;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UrlRequestDto {

    @NotBlank
    private String urlLonga;
}
