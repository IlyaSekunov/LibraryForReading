package ru.sekunov.library.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sekunov.library.author.Author;
import ru.sekunov.library.genre.Genre;

@Entity
@Table(name = "Book")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String title;

    @Column(name = "page_count")
    private int pageCount;

    @OneToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Column(name = "publish_year")
    private int publishYear;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "content")
    private String contentUrl;
}
