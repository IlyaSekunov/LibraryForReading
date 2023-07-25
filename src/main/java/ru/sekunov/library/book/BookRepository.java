package ru.sekunov.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sekunov.library.genre.Genre;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByGenre(Genre genre);
    List<Book> findByTitleContains(String sequence);
}
