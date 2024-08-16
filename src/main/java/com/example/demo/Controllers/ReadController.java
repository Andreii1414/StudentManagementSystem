package com.example.demo.Controllers;

import com.example.demo.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReadController {
    private final Database database;
    @Autowired
    public ReadController(Database database) {
        this.database = database;
    }

    @GetMapping("/top10")
    public List<String> top10() {
        return database.top10();
    }

    @GetMapping("/diriginti")
    public List<String> diriginti() {
        return database.getDiriginti();
    }

    @GetMapping("/top5")
    public List<String> top5() {
        return database.top5();
    }

    @GetMapping("/burse")
    public List<String> burse() {
        return database.burse();
    }

    @GetMapping("/tipBurse")
    public List<String> tipBurse() {
        return database.tipBursa();
    }

    @GetMapping("/clase")
    public List<String> clase() {
        return database.clase();
    }

    @GetMapping("/materii")
    public List<String> materii() {
        return database.materii();
    }
}
