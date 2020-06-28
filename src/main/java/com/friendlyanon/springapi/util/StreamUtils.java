package com.friendlyanon.springapi.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toCollection;

@UtilityClass
public class StreamUtils {
    public <T, C extends Collection<T>> Collector<T, ?, C> intoArrayList() {
        return toCollection(StreamUtils::listFactory);
    }

    @SuppressWarnings("unchecked")
    private <T, C extends Collection<T>> C listFactory() {
        return (C) new ArrayList<T>();
    }
}
