package com.rapidcycling.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.HttpStatus;

class ErrorPageResolverTest {

    private final ErrorPageResolver resolver = new ErrorPageResolver(new DefaultResourceLoader());

    @Test
    void usesExactPageWhenItExists() {
        assertThat(resolver.chooseView(HttpStatus.NOT_FOUND)).isEqualTo("error/404");
        assertThat(resolver.chooseView(HttpStatus.FORBIDDEN)).isEqualTo("error/403");
    }

    @Test
    void fallsBackToGenericPageWhenNoSpecificPageExists() {
        assertThat(resolver.chooseView(HttpStatus.INTERNAL_SERVER_ERROR)).isEqualTo("error/error");
        assertThat(resolver.chooseView(HttpStatus.METHOD_NOT_ALLOWED)).isEqualTo("error/error");
    }
}
