package ru.sekunov.library.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sekunov.library.author.Author;
import ru.sekunov.library.author.AuthorDAO;
import ru.sekunov.library.book.Book;
import ru.sekunov.library.book.BookDAO;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;

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
            authors.add(authorDAO.getAuthorById(book.getAuthorId()));
        }
        return authors;
    }

    public List<Book> bookListAccordingToParams(String genreId, String letter, String searchString, String searchOption) {
        List<Book> bookList;
        if (genreId != null) {
            bookList = bookDAO.getBooksByGenre(Integer.parseInt(genreId));
        } else if (letter != null) {
            bookList = bookDAO.getBooksByLetter(letter);
        } else if (searchString != null) {
            BookDAO.SearchType searchType = BookDAO.SearchType.TITLE;
            if (searchOption.equals("Автор")) {
                searchType = BookDAO.SearchType.AUTHOR;
            }
            if (!searchString.trim().equals("")) {
                bookList = bookDAO.getBooksBySearch(searchString, searchType);
            } else {
                bookList = bookDAO.getAllBooks();
            }
        } else {
            bookList = bookDAO.getAllBooks();
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
