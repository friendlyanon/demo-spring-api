package com.friendlyanon.springapi.filter;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("api/v1/hash")
public class HashPostFilter extends HttpFilter {
    @NonNull
    @Value("${secrets.spring-api.auth-key}")
    private static String AUTH_KEY;

    @Override
    @SneakyThrows
    public void doFilter(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) {
        val canSaveHashes = request.getMethod().equals("POST")
            && !AUTH_KEY.equals(request.getHeader("x-hash-auth-key"));

        if (canSaveHashes) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
