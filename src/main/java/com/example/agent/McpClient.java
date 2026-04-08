package com.example.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class McpClient {

    private final RestClient restClient;

    public McpClient(@Value("${mcp.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Tool("Creates a new GitHub issue with a title and a body")
    public String createIssue(String title, String body) {
        return restClient.post()
                .uri("/tools/create_issue")
                .body(new McpCreateIssueRequest(title, body))
                .retrieve()
                .body(String.class);
    }
}