package ru.sekunov.library.book;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private int id;
    private String title;
    private int pageCount;
    private int genreId;
    private int authorId;
    private int publishYear;
    private String imageUrl;
    private String contentUrl;
}
