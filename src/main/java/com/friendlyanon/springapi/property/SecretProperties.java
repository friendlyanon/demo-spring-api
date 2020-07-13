package com.friendlyanon.springapi.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@ConfigurationProperties("secrets")
@Getter
@Setter
public class SecretProperties {
    /**
     * Clients must send this key with the x-hash-auth-key header if they wish
     * to persist new hashes into the database.
     */
    private String authKey;
}
