package com.rapidcycling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Values that every Thymeleaf page needs, so each controller does not have to add them.
 */
@ControllerAdvice(annotations = Controller.class)
public class GlobalModelAdvice {

    /** Used by the sidebar to highlight the current page. */
    @ModelAttribute("currentPath")
    public String currentPath(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
