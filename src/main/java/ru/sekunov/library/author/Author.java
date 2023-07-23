package ru.sekunov.library.author;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Author {
    private int id;
    private String fio;
    private Date birthday;
}
