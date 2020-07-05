package com.friendlyanon.springapi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Nullable;

@Controller
public class ErrorRedirectController implements ErrorController {
    @RequestMapping("${server.error.path}")
    public String error() {
        return "redirect:/";
    }

    /**
     * Implementing this method is required, but it's unused, so it just returns
     * null.
     */
    @Override
    @Nullable
    public String getErrorPath() {
        return null;
    }
}
