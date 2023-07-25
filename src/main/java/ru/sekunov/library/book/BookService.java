package ru.sekunov.library.book;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sekunov.library.genre.Genre;
import ru.sekunov.library.genre.GenreRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    public enum SearchType {
        TITLE, AUTHOR
    }

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final EntityManager entityManager;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAllByGenreId(int genreId) {
        List<Book> books = null;
        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        if (genreOptional.isPresent()) {
            Genre genre = genreOptional.get();
            books = bookRepository.findAllByGenre(genre);
        }
        return books;
    }

    public List<Book> findAllContainsLetter(String letter) {
        return bookRepository.findByTitleContains(letter);
    }

    public List<Book> findAllBySearch(String searchString, SearchType searchType) {
        List<Book> books;
        if (searchType == SearchType.AUTHOR) {
            Session session = entityManager.unwrap(Session.class);
            books = session.createQuery("from Book as book left join fetch book.author as author " +
                    "where author.fio like :searchString", Book.class)
                    .setParameter("searchString", "%" + searchString + "%").getResultList();
        } else {
            books = bookRepository.findByTitleContains(searchString);
        }
        return books;
    }
}
