package com.example.agent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgentControllerIT {

    @Autowired
    private WebTestClient web;

    @MockitoBean 
    private McpClient mcp;

    @Test
    void should_call_endpoint() {
        when(mcp.callTool(eq("create_issue"), anyMap()))
            .thenReturn(Mono.just(Map.of("number", 1, "html_url", "http://github.com/test")));

        web.post().uri("/api/run")
            .bodyValue(Map.of("prompt", "Create a task to add OpenTelemetry"))
            .exchange()
            .expectStatus().isOk();
    }
}