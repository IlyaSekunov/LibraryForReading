package ru.sekunov.library.main;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sekunov.library.author.Author;
import ru.sekunov.library.book.Book;
import ru.sekunov.library.genre.GenreDAO;
import ru.sekunov.library.util.CustomizeHelper;
import ru.sekunov.library.util.LetterList;

import java.util.List;

@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;
    private final CustomizeHelper customizeHelper;
    private final GenreDAO genreDAO;

    @GetMapping
    public String mainPage(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "genre_id", required = false) String genreId,
                           @RequestParam(value = "letter", required = false) String letter,
                           @RequestParam(value = "search_string", required = false) String searchString,
                           @RequestParam(value = "search_option", required = false) String searchOption,
                           Model model) {

        List<Book> allBooksFound = libraryService.bookListAccordingToParams(genreId, letter, searchString, searchOption);
        model.addAttribute("booksFound", allBooksFound.size());

        List<Book> booksOnCurrentPage = libraryService.getBooksOnCurrentPage(page, allBooksFound);
        model.addAttribute("booksOnCurrentPage", booksOnCurrentPage);

        List<Author> authors = libraryService.getBooksAuthors(booksOnCurrentPage);
        model.addAttribute("booksAuthors", authors);

        String stringOfParameters = libraryService.getStringOfParameters(genreId, letter, searchString, searchOption);
        model.addAttribute("parameters", stringOfParameters);

        model.addAttribute("letterList", LetterList.getRussianLetters());
        model.addAttribute("customizeHelper", customizeHelper);
        model.addAttribute("genres", genreDAO.getAllGenres());
        model.addAttribute("currentPage", page);

        int pagesCount = (int) Math.ceil(allBooksFound.size() / 3.);
        List<Integer> pagesArray = libraryService.getPagesArray(pagesCount);
        model.addAttribute("pages", pagesArray);
        model.addAttribute("pagesCount", pagesCount);

        return "mainPage";
    }
}
