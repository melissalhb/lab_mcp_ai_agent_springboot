package com.example.agent.tools;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import java.util.Map;
import com.example.agent.McpClient;
import com.example.agent.GitHubMcpTools;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GitHubMcpToolsTest {

    @Test
    void should_call_mcp_tool() {
        McpClient mcp = mock(McpClient.class);
        
        when(mcp.callTool(eq("create_issue"), anyMap()))
            .thenReturn(Mono.just(Map.of("number", 42, "html_url", "https://github.com/test/repo/issues/42")));

        GitHubMcpTools tools = new GitHubMcpTools(mcp, "owner", "repo");
        String result = tools.createIssue("Mon Titre", "Ma Description");

        assertTrue(result.contains("Issue created"));
        
        verify(mcp, times(1)).callTool(eq("create_issue"), anyMap());
    }
}