package com.friendlyanon.springapi.model.converter;

import lombok.val;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.friendlyanon.springapi.model.converter.HashValue.HASH_LENGTH;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * This class does the conversion from BINARY that is in the database to a Java
 * string and vice versa. This allows for less space usage in the database at
 * the cost of some minimal CPU time to convert that hash back and forth.
 */
@Converter(autoApply = true)
public class HashValueConverter implements AttributeConverter<HashValue, byte[]> {
    private static byte[] toByteArray(String hex) {
        val bytes = new byte[HASH_LENGTH / 2];

        for (int i = 0; i < HASH_LENGTH; i += 2) {
            val chunk = hex.substring(i, i + 2);

            bytes[i / 2] = (byte) Integer.parseInt(chunk, 16);
        }

        return bytes;
    }

    @Override
    public byte[] convertToDatabaseColumn(HashValue attribute) {
        val hash = attribute.getValue();
        assert hash.length() == HASH_LENGTH
            : "hash length must be " + HASH_LENGTH + ", got " + hash.length();

        return toByteArray(hash);
    }

    private static String toByteHex(byte b) {
        return leftPad(Integer.toString(Byte.toUnsignedInt(b), 16), 2, '0');
    }

    @Override
    public HashValue convertToEntityAttribute(byte[] bytes) {
        assert bytes.length == HASH_LENGTH / 2
            : "bytes length must be " + HASH_LENGTH / 2 + ", got " + bytes.length;

        val sb = new StringBuilder(HASH_LENGTH);
        for (val b : bytes) {
            sb.append(toByteHex(b));
        }

        return new HashValue(sb.toString());
    }
}
