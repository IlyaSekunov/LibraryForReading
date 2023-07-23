package ru.sekunov.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sekunov.library.row_mappers.BookRowMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDAO {

    public enum SearchType {
        TITLE, AUTHOR
    }

    private final JdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from book", new BookRowMapper());
    }

    public List<Book> getBooksByGenre(int genreId) {
        return jdbcTemplate.query("select * from book where genre_id = ?", new Object[]{genreId}, new BookRowMapper());
    }

    public List<Book> getBooksByLetter(String letter) {
        return jdbcTemplate.query("select * from book where name like '%" + letter + "%' order by name",
                new BookRowMapper());
    }

    public List<Book> getBooksBySearch(String searchString, SearchType searchType) {
        StringBuilder query = new StringBuilder("select * from book ");
        if (searchType == SearchType.AUTHOR) {
            query.append("inner join author on author.id = book.author_id " +
                    "where lower(author.fio) like '%" + searchString.toLowerCase() +
                    "%' order by book.name");
        } else if (searchType == SearchType.TITLE) {
            query.append("where lower(name) like '%" + searchString.toLowerCase() + "%' order by name");
        }
        return jdbcTemplate.query(query.toString(), new BookRowMapper());
    }
}
