package ru.sekunov.library.author;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorDAO {

    private final JdbcTemplate jdbcTemplate;

    public Author getAuthorById(int id) {
        return jdbcTemplate.query("select * from author where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Author.class)).stream().findAny().orElse(null);
    }
}
