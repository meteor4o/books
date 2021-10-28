package bg.softuni.books.model.dto;

import javax.validation.constraints.NotNull;

public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private AuthorDTO author;

    @NotNull
    public Long getId() {
        return id;
    }

    public BookDTO setId(Long id) {
        this.id = id;
        return this;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotNull
    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    @NotNull
    public AuthorDTO getAuthor() {
        return author;
    }

    public BookDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }
}
