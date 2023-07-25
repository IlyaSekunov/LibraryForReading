package ru.sekunov.library.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author findAuthorById(int authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }
}
