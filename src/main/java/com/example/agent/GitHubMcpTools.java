package com.example.agent;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class GitHubMcpTools {
    private final McpClient mcp;
    private final String owner;
    private final String repo;

    public GitHubMcpTools(McpClient mcp, 
                          @Value("${github.owner}") String owner, 
                          @Value("${github.repo}") String repo) {
        this.mcp = mcp; this.owner = owner; this.repo = repo;
    }

    @Tool("Create a GitHub issue.")
    public String createIssue(@P("The title") String title, @P("The description") String body) {
        Map result = (Map) mcp.callTool("create_issue", Map.of(
                "owner", owner, "repo", repo, "title", title, "body", body
        )).block();
        return "Issue created: " + result;
    }
}