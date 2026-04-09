package com.example.agent;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;
import java.util.UUID;

@Component
public class McpClient {
    private final WebClient webClient;

    public McpClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:3001").build(); 
    }

    public Mono<Object> callTool(String toolName, Map<String, Object> arguments) {
        return webClient.post()
                .uri("/mcp")
                .bodyValue(Map.of(
                    "jsonrpc", "2.0",
                    "id", UUID.randomUUID().toString(),
                    "method", "tools/call",
                    "params", Map.of("name", toolName, "arguments", arguments)
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> resp.get("result"));
    }
}