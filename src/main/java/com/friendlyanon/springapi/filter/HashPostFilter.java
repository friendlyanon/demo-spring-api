package com.friendlyanon.springapi.filter;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/api/v1/hash")
public class HashPostFilter extends HttpFilter {
    private String authKey = "";

    private static String getAuthKeyFromApplicationContext(
        FilterConfig filterConfig
    ) {
        val ctx = filterConfig.getServletContext();
        val env = WebApplicationContextUtils
            .getRequiredWebApplicationContext(ctx)
            .getEnvironment();
        val authKey = env.getProperty("secrets.auth-key");

        Assert.notNull(authKey, "authKey must not be null");
        return authKey;
    }

    @Override
    @SneakyThrows
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);

        authKey = getAuthKeyFromApplicationContext(filterConfig);
    }

    @Override
    @SneakyThrows
    public void doFilter(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) {
        if ("POST".equals(request.getMethod())) {
            val canSaveHashes = authKey
                .equals(request.getHeader("x-hash-auth-key"));

            if (!canSaveHashes) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
