package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Database {
    private final JdbcTemplate jdbcTemplate;
    public Database(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void register(String name, String email, String password) {
        jdbcTemplate.update("INSERT INTO users (nume, email, parola) VALUES (?, ?, ?)", name, email, password);
    }

    public List<String> getAllEmails(){
        return jdbcTemplate.query("SELECT email FROM users", (rs, rowNum) -> rs.getString("email"));
    }

    public boolean login(String email, String password) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE email = ? AND parola = ?", Integer.class, email, password) == 1;
    }

    public List<String> top10() {
        List<String> list = jdbcTemplate.query(
                "select * from (" +
                        "  select e.nume, e.prenume, avg(n.valoare) as avg_valoare, c.nume_clasa " +
                        "  from elev e " +
                        "  natural join nota n " +
                        "  join clasa c on e.id_clasa = c.id_clasa " +
                        "  group by e.id_elev, e.nume, e.prenume, c.nume_clasa " +
                        "  order by avg_valoare desc" +
                        ") where rownum < 11;",
                (rs, rowNum) -> rs.getString("nume") + " " + rs.getString("prenume") + " " + rs.getString("avg_valoare") + " " + rs.getString("nume_clasa")
        );
        return list;
    }

    public boolean isAdmin(String email) {
        String sql = "SELECT admin FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE email = ? AND admin = TRUE", Integer.class, email) == 1;
    }

    public List<String> getDiriginti()
    {
        return jdbcTemplate.query(
                "SELECT p.nume, p.prenume, nume_materie, nume_clasa, " +
                        "COUNT(e.id_elev) AS nr_elevi, specializare, extra " +
                        "FROM profesor p " +
                        "JOIN clasa c ON p.id_profesor = c.id_diriginte " +
                        "JOIN elev e ON e.id_clasa = c.id_clasa " +
                        "JOIN profesormaterie pm ON pm.id_profesor = p.id_profesor " +
                        "JOIN materie m ON m.id_materie = pm.id_materie " +
                        "GROUP BY e.id_clasa, nume_clasa, p.nume, p.prenume, nume_materie, specializare, extra;",
                (rs, rowNum) ->
                        rs.getString("nume") + ";" +
                                rs.getString("prenume") + ";" +
                                rs.getString("nume_materie") + ";" +
                                rs.getString("nume_clasa") + ";" +
                                rs.getString("nr_elevi") + ";" +
                                rs.getString("specializare") + ";" +
                                rs.getString("extra")
        );
    }

    public List<String> top5(){
        List<String> list = jdbcTemplate.query(
                "SELECT * FROM (" +
                        "    SELECT nume_materie, COUNT(a.id_materie) AS nr_absente " +
                        "    FROM absenta a " +
                        "    JOIN materie m ON a.id_materie = m.id_materie " +
                        "    GROUP BY a.id_materie, nume_materie " +
                        "    ORDER BY 2 DESC" +
                        ") " +
                        "WHERE ROWNUM < 6;",
                (rs, rowNum) -> rs.getString("nume_materie") + ";" + rs.getString("nr_absente")
        );

        return list;

    }

    public List<String> burse(){
        List<String> list = jdbcTemplate.query(
                "SELECT nume, prenume, nume_clasa " +
                        "FROM bursa b1 " +
                        "JOIN tipbursa tb1 ON b1.id_tipbursa = tb1.id_tipbursa " +
                        "JOIN bursa b2 ON b1.id_elev = b2.id_elev " +
                        "JOIN tipbursa tb2 ON b2.id_tipbursa = tb2.id_tipbursa " +
                        "JOIN elev e on e.id_elev = b1.id_elev " +
                        "JOIN clasa c on c.id_clasa = e.id_clasa " +
                        "WHERE b1.semestru = 2 " +
                        "  AND b2.semestru = 2 " +
                        "  AND tb1.denumire = 'Sociala' " +
                        "  AND tb2.denumire = 'Merit' " +
                        "  AND b1.id_elev = b2.id_elev " +
                        "ORDER BY e.nume, prenume;",
                (rs, rowNum) -> rs.getString("nume") + " " + rs.getString("prenume") + " " + rs.getString("nume_clasa")
        );

        return list;
    }

    public List<String> tipBursa(){
        List<String> list = jdbcTemplate.query(
                "SELECT id_tipbursa, denumire FROM tipbursa;",
                (rs, rowNum) -> rs.getString("id_tipbursa") + " " + rs.getString("denumire")
        );

        return list;
    }

    public List<String> clase(){
        List<String> list = jdbcTemplate.query(
                "SELECT id_clasa, nume_clasa FROM clasa;",
                (rs, rowNum) -> rs.getString("id_clasa") + " " + rs.getString("nume_clasa"));
        return list;
    }

    public void adaugaElev(String nume, String prenume, String dataNasterii, String tipBursa, String semestru, String idClasa) {
        jdbcTemplate.update("INSERT INTO elev (nume, prenume, data_nasterii, id_clasa) VALUES (?, ?, ?, ?)", nume, prenume, dataNasterii, idClasa);
        if(semestru != null) {
            int idElev = jdbcTemplate.queryForObject("SELECT MAX(id_elev) FROM elev", Integer.class);
            jdbcTemplate.update("INSERT INTO bursa (id_elev, id_tipbursa, semestru) VALUES (?, ?, ?)", idElev, tipBursa, semestru);
        }
    }

    public boolean mutaElev(String idElev, String idClasa) {
        return jdbcTemplate.update("UPDATE elev SET id_clasa = ? WHERE id_elev = ?", idClasa, idElev) == 1;
    }

    public List<String> materii(){
        List<String> list = jdbcTemplate.query(
                "SELECT id_materie, nume_materie FROM materie;",
                (rs, rowNum) -> rs.getString("id_materie") + ";" + rs.getString("nume_materie"));
        return list;
    }

    public boolean motiveazaAbsenta(String idElev, String dataAbsenta, String semestru, String idMaterie) {
        return jdbcTemplate.update("DELETE FROM absenta WHERE id_elev = ? AND data_absenta = ? AND semestru = ? AND id_materie = ?", idElev, dataAbsenta, semestru, idMaterie) == 1;
    }

}
