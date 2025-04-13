package com.url_shortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.url_shortener.dto.error.ErrorResponseDto;
import com.url_shortener.exception.UrlInvalidaException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlInvalidaException.class)
    public ResponseEntity<ErrorResponseDto> handleUrlInvalidaException(UrlInvalidaException e) {
        ErrorResponseDto dto = new ErrorResponseDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String mensagemErro = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("Erro de validação");

        ErrorResponseDto dto = new ErrorResponseDto(mensagemErro);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}
