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

   /* @GetMapping
    public ResponseEntity<String> chatGet(@RequestHeader("Conversation-Id") String conversationId,
                                          @RequestBody String message) {
        return ResponseEntity.ok(chatService.chat(message, conversationId));
    }*/

    @GetMapping
    public ResponseEntity<String> chatGet(@RequestBody String message) {
      //  String response = "You said: " + message;
      log.info("Received chat request: {}", message);
        return ResponseEntity.ok(chatService.chat(message));
    }
}
