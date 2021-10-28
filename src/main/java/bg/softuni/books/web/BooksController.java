package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = booksService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
       Optional<BookDTO> book = booksService.getBookById(id);
       if(book.isEmpty()) {
           return ResponseEntity.notFound().build();

       } else {
           return ResponseEntity.ok(book.get());
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long id) {
        booksService.deleteBookById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO, UriComponentsBuilder uriComponentsBuilder) {

        long bookId = booksService.createBook(bookDTO);

        URI location = uriComponentsBuilder.path("/books/{id}")
                .buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }


}
