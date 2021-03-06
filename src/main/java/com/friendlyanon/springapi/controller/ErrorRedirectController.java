package com.friendlyanon.springapi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("errorRedirectController")
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
