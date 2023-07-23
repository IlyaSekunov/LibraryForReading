package ru.sekunov.library.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Genre> getAllGenres() {
        return jdbcTemplate.query("select * from genre", new BeanPropertyRowMapper<>(Genre.class));
    }
}
