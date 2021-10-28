package bg.softuni.books.service;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.BookEntity;

import java.util.List;

public interface BooksService {

    List<BookDTO> getAllBooks();

    <Optional> java.util.Optional<BookDTO> getBookById(Long id);

    void deleteBookById(Long id);

    long createBook(BookDTO bookDTO);
}
