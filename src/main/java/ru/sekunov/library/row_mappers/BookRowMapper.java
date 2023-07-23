package ru.sekunov.library.row_mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sekunov.library.book.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt(1));
        book.setTitle(rs.getString(2));
        book.setPageCount(rs.getInt(3));
        book.setGenreId(rs.getInt(4));
        book.setAuthorId(rs.getInt(5));
        book.setPublishYear(rs.getInt(6));
        book.setImageUrl(rs.getString(7));
        book.setContentUrl(rs.getString(8));
        return book;
    }
}
