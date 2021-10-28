package bg.softuni.books.init;

import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BooksInit implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BooksInit(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (bookRepository.count() == 0 || authorRepository.count() == 0) {
            initJovkov();
            initHaitov();
            initTalev();
            initElinPelin();
            initVazov();
        }
    }

    private void initVazov() {
        initAuthor("Иван Вазов", "Под игото", "Пряпорец и гусла");
    }

    private void initElinPelin() {
        initAuthor("Елин Пелин", "Ян Бибиян", "Пижо и Пендо");
    }

    private void initHaitov() {
        initAuthor("Николай Хайтов", "Диви разкази");
    }

    private void initJovkov() {
        initAuthor("Йордан Йовков", "Старопланински легенди", "Чифликът...");
    }

    private void initTalev() {
        initAuthor("Димитър Талев", "Тютюн", "Железният светилник");
    }


    private void initAuthor(String authorName, String... books) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorName);

        List<BookEntity> allBooks = new ArrayList<>();

        for (String book : books) {
            BookEntity newBook = new BookEntity();
            newBook.setAuthor(author);
            newBook.setTitle(book);
            newBook.setIsbn(UUID.randomUUID().toString());
            allBooks.add(newBook);
        }
        author.setBooks(allBooks);
        bookRepository.saveAll(allBooks);
    }
}
