package com.friendlyanon.springapi;

import com.friendlyanon.springapi.model.converter.HashValueConverter;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.friendlyanon.springapi.model.converter.HashValue.HASH_LENGTH;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpringApiApplicationTests {
    @Test
    public void noop() {
    }

    @Test
    public void givenZeroSizedByteArray_whenConvertingToHashValue_thenExceptionIsThrown() {
        val bytes = new byte[0];
        val converter = new HashValueConverter();

        assertThrows(
            AssertionError.class,
            () -> converter.convertToEntityAttribute(bytes)
        );
    }

    @Test
    public void givenProperlySizedByteArray_whenConvertingToHashValue_thenConversionIsReversible() {
        val converter = new HashValueConverter();
        val bytes = new byte[HASH_LENGTH / 2];
        new Random().nextBytes(bytes);

        val value = converter.convertToEntityAttribute(bytes);
        assertArrayEquals(converter.convertToDatabaseColumn(value), bytes);
    }
}
