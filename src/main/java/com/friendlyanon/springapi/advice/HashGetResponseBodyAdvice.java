package com.friendlyanon.springapi.advice;

import com.friendlyanon.springapi.json.serializer.ListOfHashSerializer;
import com.friendlyanon.springapi.model.Hash;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice("hashGetResponseBodyAdvice")
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
        @Nullable Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        if (body == null) {
            throw new NullPointerException("unreachable");
        }

        return new HashGetWrapper((List<Hash>) body);
    }

    @JsonSerialize(using = ListOfHashSerializer.class)
    @Value
    public static class HashGetWrapper {
        List<Hash> hashes;
    }
}
