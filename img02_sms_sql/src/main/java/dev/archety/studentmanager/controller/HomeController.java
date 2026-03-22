package dev.archety.studentmanager.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "application", "Student Manager API",
                "status", "running",
                "mainEndpoints", new String[]{
                        "/api/students",
                        "/api/students/count",
                        "/api/students/version",
                        "/actuator/health",
                        "/actuator/info"
                }
        );
    }
}