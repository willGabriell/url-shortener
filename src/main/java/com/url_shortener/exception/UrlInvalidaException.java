package com.url_shortener.exception;

public class UrlInvalidaException extends RuntimeException{
    public UrlInvalidaException(String m) {
        super(m);
    }
}
