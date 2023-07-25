package ru.sekunov.library.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sekunov.library.author.Author;
import ru.sekunov.library.author.AuthorService;
import ru.sekunov.library.book.Book;
import ru.sekunov.library.book.BookService;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookService bookService;
    private final AuthorService authorService;

    public List<Integer> getPagesArray(int pagesCount) {
        List<Integer> pagesArray = new ArrayList<>();
        for (int i = 1; i <= pagesCount; ++i) {
            pagesArray.add(i);
        }
        return pagesArray;
    }

    public List<Book> getBooksOnCurrentPage(int page, List<Book> allBooksFound) {
        int startBookIndex = 3 * (page - 1);
        int endBookIndex = startBookIndex + 2 >= allBooksFound.size() ? allBooksFound.size() - 1 :startBookIndex + 2;
        return allBooksFound.subList(startBookIndex, endBookIndex + 1);
    }

    public List<Author> getBooksAuthors(List<Book> books) {
        List<Author> authors = new ArrayList<>();
        for (Book book : books) {
            authors.add(authorService.findAuthorById(book.getAuthor().getId()));
        }
        return authors;
    }

    public List<Book> bookListAccordingToParams(String genreId, String letter, String searchString, String searchOption) {
        List<Book> bookList;
        if (genreId != null) {
            bookList = bookService.findAllByGenreId(Integer.parseInt(genreId));
        } else if (letter != null) {
            bookList = bookService.findAllContainsLetter(letter);
        } else if (searchString != null && searchOption != null) {
            BookService.SearchType searchType = BookService.SearchType.TITLE;
            if (searchOption.equals("Автор")) {
                searchType = BookService.SearchType.AUTHOR;
            }
            if (!searchString.trim().equals("")) {
                bookList = bookService.findAllBySearch(searchString, searchType);
            } else {
                bookList = bookService.findAll();
            }
        } else {
            bookList = bookService.findAll();
        }
        return bookList;
    }

    public String getStringOfParameters(String genreId, String letter, String searchString, String searchOption) {
        StringBuilder parameters = new StringBuilder();
        if (genreId != null) {
            parameters.append("&genre_id=" + genreId);
        }
        if (letter != null) {
            parameters.append("&letter=" + letter);
        }
        if (searchString != null) {
            parameters.append("&search_string=" + searchString);
        }
        if (searchOption != null) {
            parameters.append("&search_option=" + searchOption);
        }
        return parameters.toString();
    }
}
