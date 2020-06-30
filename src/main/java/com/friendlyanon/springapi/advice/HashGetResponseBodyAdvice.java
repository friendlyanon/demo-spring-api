package com.friendlyanon.springapi.advice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.util.serializer.HashListSerializer;
import lombok.Value;
import lombok.val;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice("hash")
public class HashGetResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(
        MethodParameter returnType,
        Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return returnType.getMethodAnnotation(GetMapping.class) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        val hashes = (List<Hash>) body;

        return new HashGetWrapper(hashes);
    }

    @JsonSerialize(using = HashListSerializer.class)
    @Value
    public static class HashGetWrapper {
        List<Hash> hashes;
    }
}