package com.migrosone.couriertracking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migrosone.couriertracking.payload.response.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Configuration
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        GenericResponse<Object> customResponse = GenericResponse.error("You are unauthorized for this request!");
        String jsonResponse = new ObjectMapper().writeValueAsString(customResponse);
        response.getWriter().write(jsonResponse);
    }
}
