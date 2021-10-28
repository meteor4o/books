package bg.softuni.books.service.impl;

import bg.softuni.books.model.dto.AuthorDTO;
import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import bg.softuni.books.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public BooksServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(this::asBook)
                .collect(Collectors.toList());

    }

    @Override
    public <Optional> java.util.Optional<BookDTO> getBookById(Long id) {

        return bookRepository.findById(id)
                .map(this::asBook);
    }

    @Override
    public void deleteBookById(Long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        bookRepository.delete(book);
    }

    @Override
    public long createBook(BookDTO bookDTO) {

    AuthorEntity author = authorRepository.findByName(bookDTO.getAuthor().getName())
          .orElseGet(() ->
                 new AuthorEntity().setName(bookDTO.getAuthor().getName()));

    BookEntity newBook = new BookEntity()
            .setAuthor(author)
            .setIsbn(bookDTO.getIsbn())
            .setTitle(bookDTO.getTitle());

    return bookRepository.save(newBook).getId();
    }

    private BookDTO asBook(BookEntity book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO authorDTO = modelMapper.map(book.getAuthor(), AuthorDTO.class);
        bookDTO.setAuthor(authorDTO);

        return bookDTO;
    }

}
