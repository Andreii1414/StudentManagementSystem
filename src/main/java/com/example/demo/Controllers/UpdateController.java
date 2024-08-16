package com.example.demo.Controllers;

import com.example.demo.Database;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UpdateController {
    private final Database database;
    private final RestTemplate restTemplate;
    @Autowired
    public UpdateController(Database database) {
        this.database = database;
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/adaugaElev")
    public ResponseEntity<String> adaugaElev(
            @RequestParam("nume") String nume,
            @RequestParam("prenume") String prenume,
            @RequestParam("data_nasterii") String dataNasterii,
            @RequestParam("tip_bursa") String tipBursa,
            @RequestParam(value = "semestru", required = false) String semestru,
            @RequestParam("clasa") String idClasa,
            HttpServletRequest request) {

        String cookieValue = request.getHeader("Cookie");
        HttpHeaders headers = new HttpHeaders();
        if (cookieValue != null) {
            headers.set("Cookie", cookieValue);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8888/isAdmin", HttpMethod.GET, entity, String.class);

        if (response.getBody() != null && "admin".equals(response.getBody())) {
            database.adaugaElev(nume, prenume, dataNasterii, tipBursa, semestru, idClasa);
            return ResponseEntity.ok("Elev adaugat cu succes");
        } else {
            return ResponseEntity.status(403).body("Acces interzis: Nu aveți permisiunea necesară");
        }
    }

    @PostMapping("/mutaElev")
    public ResponseEntity<String> mutaElev(
            @RequestParam("id_elev") String idElev,
            @RequestParam("clasa") String idClasa,
            HttpServletRequest request) {

        String cookieValue = request.getHeader("Cookie");
        HttpHeaders headers = new HttpHeaders();
        if (cookieValue != null) {
            headers.set("Cookie", cookieValue);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8888/isAdmin", HttpMethod.GET, entity, String.class);

        if (response.getBody() != null && "admin".equals(response.getBody())) {
            boolean exists = database.mutaElev(idElev, idClasa);
            if (!exists) {
                return ResponseEntity.status(404).body("Elevul nu există");
            }
            return ResponseEntity.ok("Elev mutat cu succes");
        } else {
            return ResponseEntity.status(403).body("Acces interzis: Nu aveți permisiunea necesară");
        }
    }

    @PostMapping("/motiveazaAbsenta")
    public ResponseEntity<String> motiveazaAbsenta(
            @RequestParam("id_elev") String idElev,
            @RequestParam("data_absenta") String dataAbsenta,
            @RequestParam("semestru") String semestru,
            @RequestParam("id_materie") String idMaterie,
            HttpServletRequest request) {

        String cookieValue = request.getHeader("Cookie");
        HttpHeaders headers = new HttpHeaders();
        if (cookieValue != null) {
            headers.set("Cookie", cookieValue);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8888/isAdmin", HttpMethod.GET, entity, String.class);

        if (response.getBody() != null && "admin".equals(response.getBody())) {
            boolean exists = database.motiveazaAbsenta(idElev, dataAbsenta, semestru, idMaterie);
            if (!exists) {
                return ResponseEntity.status(404).body("Datele introduse nu corespund niciunei absențe");
            }
            return ResponseEntity.ok("Absența motivată cu succes");
        } else {
            return ResponseEntity.status(403).body("Acces interzis: Nu aveți permisiunea necesară");
        }
    }

}
