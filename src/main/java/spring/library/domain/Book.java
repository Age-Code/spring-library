package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private int publicationYear;

    @OneToMany(mappedBy = "book")
    private List<LibraryBook> libraryBookList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Purchase> purchaseList = new ArrayList<>();

    // 생성자
    protected Book() {}
    private Book(String title, String author, String publisher, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }
    public static Book of(String title, String author, String publisher, int publicationYear) {
        return new Book(title, author, publisher, publicationYear);
    }

}
