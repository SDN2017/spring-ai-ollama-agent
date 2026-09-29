package com.shalinidev.spring.ai.agent.config;

import com.shalinidev.spring.ai.agent.tools.OrderTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AiClientConfig {
    private final OrderTools orderTools;

    @Bean
    public ChatClient chatClient(
            ChatClient.Builder builder) {
        return builder
                .defaultSystem(
                        """
                                        You are a helpful customer support agent to get the status of their orders.
                                       """)
                //.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory()).build())
                .defaultTools(orderTools)
                .build();
    }

}
