package spring.library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;
    private Long amount;

    // 생성자
    protected Book() {}
    private Book(String title, String author, String publisher, Long publicationYear, String classification, String status, Long amount) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.classification = classification;
        this.status = status;
        this.amount = amount;
    }
    public static Book of(String name, String author, String publisher, Long publicationYear, String classification, String status, Long amount) {
        return new Book(name, author, publisher, publicationYear, classification, status, amount);
    }

}
