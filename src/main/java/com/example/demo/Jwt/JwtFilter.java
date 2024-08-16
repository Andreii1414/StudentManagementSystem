package com.example.demo.Jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class JwtFilter implements jakarta.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = null;
        if (httpRequest.getCookies() != null) {
            for (Cookie cookie : httpRequest.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && JwtUtils.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }

        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
