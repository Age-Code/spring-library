package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int purchaseRequestCount;
    private LocalDate requestDate;
    private String dateOfProcess;
    private String processResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 생성자
    protected Purchase() {}
    private Purchase(int purchaseRequestCount, LocalDate requestDate, String dateOfProcess, String processResult, Book book, Member member) {
        this.purchaseRequestCount = purchaseRequestCount;
        this.requestDate = requestDate;
        this.dateOfProcess = dateOfProcess;
        this.processResult = processResult;
        this.book = book;
        this.member = member;
    }
    public static Purchase of(int purchaseRequestCount, LocalDate requestDate, String dateOfProcess, String processResult, Book book, Member member) {
        return new Purchase(purchaseRequestCount, requestDate, dateOfProcess, processResult, book, member);
    }

}
