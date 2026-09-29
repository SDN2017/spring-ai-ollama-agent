package com.shalinidev.spring.ai.agent.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final ChatClient chatClient;

    public String chat(String query) {
        log.info("Starting AI chat request. Query length={}, query={}", query != null ? query.length() : 0, query);

        long startTime = System.currentTimeMillis();
        try {
            String response = chatClient
                    .prompt()
                    .user(query)
                    .call()
                    .content();

            long elapsedMs = System.currentTimeMillis() - startTime;
            log.info("AI chat request completed in {} ms. Response length={}", elapsedMs, response != null ? response.length() : 0);
            return response;
        } catch (Exception e) {
            long elapsedMs = System.currentTimeMillis() - startTime;
            log.error("AI chat request failed after {} ms. Query={}", elapsedMs, query, e);
            throw e;
        }
    }
}
