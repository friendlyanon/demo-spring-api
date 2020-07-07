package com.friendlyanon.springapi.service;

import com.friendlyanon.springapi.repository.QuoteRepository;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("quoteService")
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Nullable
    public String getRandomQuoteText() {
        return quoteRepository.findRandomQuoteText();
    }

    public String getRandomQuoteTextOr(String ifNull) {
        val result = getRandomQuoteText();

        return result == null ? ifNull : result;
    }
}
