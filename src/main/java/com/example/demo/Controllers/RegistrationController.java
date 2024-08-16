package com.example.demo.Controllers;

import com.example.demo.Database;
import com.example.demo.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
public class RegistrationController {
    private final Database database;
    @Autowired
    public RegistrationController(Database database) {
        this.database = database;
    }
    @PostMapping("/register")
    public ResponseEntity<List<String>> register(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("repeat") String repeat)
    {
        List<String> errors = new ArrayList<>();
        if(!Objects.equals(password, repeat))
            errors.add("Passwords do not match");
        if(password.length() < 8)
            errors.add("Password must be at least 8 characters long");
        if(!password.matches(".*[0-9].*"))
            errors.add("Password must contain at least one digit");
        if(password.equals(password.toLowerCase()))
            errors.add("Password must contain at least one uppercase letter");
        if(password.equals(password.toUpperCase()))
            errors.add("Password must contain at least one lowercase letter");

        List<String> emails = database.getAllEmails();
        if(emails.contains(email))
            errors.add("Email already in use");

        String hashedPassword = PasswordUtils.hashPassword(password);

        if(!errors.isEmpty())
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        database.register(name, email, hashedPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
