package com.example.demo.Controllers;

import com.example.demo.Database;
import com.example.demo.Jwt.JwtUtils;
import com.example.demo.PasswordUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    private final Database database;
    @Autowired
    public LoginController(Database database) {
        this.database = database;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletResponse response)
    {
        String hashedPassword = PasswordUtils.hashPassword(password);
        if(database.login(email, hashedPassword))
        {
            String token = JwtUtils.generateToken(email);

            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(86400);
            response.addCookie(jwtCookie);

            return new ResponseEntity<>(Map.of("message", "Login successful"), HttpStatus.OK);
        }
        List<String> error = List.of("Invalid email or password");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
