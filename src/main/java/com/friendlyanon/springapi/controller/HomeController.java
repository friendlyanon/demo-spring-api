package com.friendlyanon.springapi.controller;

import com.friendlyanon.springapi.service.QuoteService;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final QuoteService quoteService;

    @GetMapping
    public String home(Model model) {
        val quote = quoteService
            .getRandomQuoteTextOr("Nothing to see here!");
        model.addAttribute("quote", quote);

        return "home";
    }
}
