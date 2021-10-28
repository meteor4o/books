package bg.softuni.books.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {
    private String name;
    private List<BookEntity> booksByAuthor = new LinkedList<>();

    public String getName() {
        return name;
    }

    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "author")
    public List<BookEntity> getBooks() {
        return booksByAuthor;
    }

    public AuthorEntity setBooks(List<BookEntity> books) {
        this.booksByAuthor = books;
        return this;
    }

    public AuthorEntity addBook(BookEntity book) {
        this.booksByAuthor.add(book);
        return this;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
