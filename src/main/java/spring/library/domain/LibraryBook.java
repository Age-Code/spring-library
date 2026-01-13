package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class LibraryBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classification;
    private String status;
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "libraryBook")
    private List<Checkout> checkoutList = new ArrayList<>();

    // 생성자
    protected LibraryBook() {}
    private LibraryBook(String classification,String status, int amount, Book book) {
        this.classification = classification;
        this.status = status;
        this.amount = amount;
        this.book = book;
    }
    public static LibraryBook of(String classification,String status, int amount, Book book) {
        return new LibraryBook(classification ,status, amount, book);
    }

}
