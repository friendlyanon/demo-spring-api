package com.friendlyanon.springapi.filter;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/api/v1/hash")
public class HashPostFilter extends HttpFilter {
    private final String authKey;

    public HashPostFilter(
        @Value("${secrets.spring-api.auth-key}") String authKey
    ) {
        Assert.notNull(authKey, "authKey must not be null");
        this.authKey = authKey;
    }

    @Override
    @SneakyThrows
    public void doFilter(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) {
        val canSaveHashes = "POST".equals(request.getMethod())
            && authKey.equals(request.getHeader("x-hash-auth-key"));

        if (canSaveHashes) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
