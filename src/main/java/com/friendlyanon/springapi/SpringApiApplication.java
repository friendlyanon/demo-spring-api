package com.friendlyanon.springapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringApiApplication {
    public static void main(String[] args) {
        ensureAssert();
        SpringApplication.run(SpringApiApplication.class, args);
    }

    private static void ensureAssert() {
        try {
            assert false;
            throw new AssertionError("assert keyword must be enabled");
        } catch (AssertionError expected) {
            //
        }
    }
}
