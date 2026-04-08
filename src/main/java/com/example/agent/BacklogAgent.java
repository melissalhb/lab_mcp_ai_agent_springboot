package com.example.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface BacklogAgent {

    @SystemMessage("""
            You are a GitHub backlog agent.
            When the user asks to create a task, you MUST call the createIssue tool.
            The issue must have a Context, Goal, and Acceptance Criteria.
            """)
    @UserMessage("User request: {{prompt}}")
    String handle(@V("prompt") String prompt);
}