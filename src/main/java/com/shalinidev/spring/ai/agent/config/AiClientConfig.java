package com.shalinidev.spring.ai.agent.config;

import com.shalinidev.spring.ai.agent.tools.InventoryTools;
import com.shalinidev.spring.ai.agent.tools.OrderTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AiClientConfig {
    private final OrderTools orderTools;
    private final InventoryTools inventoryTools;

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory
                .builder()
                .maxMessages(10)
                .build();
    }

    @Bean
    public ChatClient chatClient(
            ChatClient.Builder builder) {
        return builder
                .defaultSystem(
                        """
                                        You are a helpful customer support agent to get the status of their orders.
                                       """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory()).build())
                .defaultTools(orderTools, inventoryTools)
                .build();
    }

}
