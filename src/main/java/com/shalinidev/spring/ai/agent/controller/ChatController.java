package com.shalinidev.spring.ai.agent.controller;


import com.shalinidev.spring.ai.agent.service.ChatService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatService chatService;

    @PostMapping(consumes = "text/plain")
    public ResponseEntity<String> chatPost(@RequestHeader("Conversation-Id") String conversationId, @RequestBody String message) {
        log.info("Received POST chat request: {}", message);
        log.info("Conversation-id: {}", conversationId);
        return ResponseEntity.ok(chatService.chat(message, conversationId));
    }
}
