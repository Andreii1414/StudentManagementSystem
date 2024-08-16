package com.example.demo.Controllers;

import com.example.demo.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    private final Database database;
    public PageController(Database database) {
        this.database = database;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
