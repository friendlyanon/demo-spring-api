package com.friendlyanon.springapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringApiApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHashes() throws Exception {
        // TODO figure out how testing works in Spring

        mockMvc
            .perform(get("/api/v1/hash").queryParam("id", "1", "2", "3", "4"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("lmao")));
    }
}
