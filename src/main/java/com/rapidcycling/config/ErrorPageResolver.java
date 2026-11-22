package com.rapidcycling.config;

import java.util.Map;

import org.springframework.boot.webmvc.autoconfigure.error.ErrorViewResolver;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Chooses which page of templates/error/ is shown when something fails.
 *
 * Search order, for example with a 404:
 * 1. error/404.html (exact code)
 * 2. error/4xx.html (any client error)
 * 3. error/error.html (generic page, always exists)
 *
 * Spring's BasicErrorController keeps handling /error: it fills the model
 * (status, error, path, timestamp) and still answers JSON to API clients.
 * This class only decides the HTML template.
 */
@Component
public class ErrorPageResolver implements ErrorViewResolver {

    private static final String FOLDER = "error/";
    private static final String GENERIC_PAGE = FOLDER + "error";

    private final ResourceLoader resourceLoader;

    public ErrorPageResolver(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        return new ModelAndView(chooseView(status), model, status);
    }

    String chooseView(HttpStatus status) {
        String exactPage = FOLDER + status.value();
        if (templateExists(exactPage)) {
            return exactPage;
        }

        String seriesPage = FOLDER + status.series().value() + "xx";
        if (templateExists(seriesPage)) {
            return seriesPage;
        }

        return GENERIC_PAGE;
    }

    private boolean templateExists(String view) {
        return resourceLoader.getResource("classpath:/templates/" + view + ".html").exists();
    }
}
