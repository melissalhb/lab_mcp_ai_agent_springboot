package com.example.agent;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AgentController {
    private final BacklogAgent agent;

    public AgentController(BacklogAgent agent) {
        this.agent = agent;
    }

    @PostMapping("/run")
    public String run(@RequestBody Map<String, String> body) {
        return agent.handle(body.get("prompt"));
    }
}