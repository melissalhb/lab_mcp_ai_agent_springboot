package com.example.agent;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
public class LangChainConfig {

    @Bean
    public AnthropicChatModel anthropicChatModel(
            @Value("${anthropic.api-key}") String apiKey,
            @Value("${anthropic.model}") String model) {
        return AnthropicChatModel.builder()
                .apiKey(apiKey)
                .modelName(model)
                .timeout(Duration.ofSeconds(60))
                .build();
    }

   @Bean
    public BacklogAgent backlogAgent(AnthropicChatModel model, McpClient mcpClient) {
        return AiServices.builder(BacklogAgent.class)
                .chatModel(model)
                .tools(mcpClient) 
                .build();
    }
}