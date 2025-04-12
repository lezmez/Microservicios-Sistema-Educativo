package com.microservice.registration.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeingClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // Obtener el token del contexto actual
        String authorizationHeader = RequestContextHolder.getRequestAttributes() != null
                ? ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization")
                : null;

        // Agregar el token al header de la solicitud Feign
        if (authorizationHeader != null) {
            template.header("Authorization", authorizationHeader);
        }
    }
}