package bg.softuni.books.model.dto;

public class AuthorDTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public AuthorDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorDTO setName(String name) {
        this.name = name;
        return this;
    }
}
