package com.example.demo.Controllers;

import com.example.demo.Database;
import com.example.demo.Jwt.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCheckController {
    private final Database database;

    public AdminCheckController(Database database) {
        this.database = database;
    }

    @GetMapping("/isAdmin")
    public String checkAdmin(@CookieValue(value = "jwt", defaultValue = "") String token, HttpServletResponse response) {
        if(!token.isEmpty() && JwtUtils.validateToken(token)) {
            String email = JwtUtils.getEmailFromToken(token);
            try {
                if (database.isAdmin(email)) {
                    return "admin";
                } else {
                    return "non-admin";
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return "error";
            }
        }
        else {
            return "invalid-token";
        }
    }


}
