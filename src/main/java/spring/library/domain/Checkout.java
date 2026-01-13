package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private int renewalCount;
    private Boolean isReturned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_book_id", nullable = false)
    private LibraryBook libraryBook;

    // 생성자
    protected Checkout() {}
    private Checkout(LocalDate loanDate ,LocalDate dueDate, int renewalCount, Boolean isReturned, Member member, LibraryBook libraryBook) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.renewalCount = renewalCount;
        this.isReturned = isReturned;
        this.member = member;
        this.libraryBook = libraryBook;
    }
    public static Checkout of(LocalDate loanDate, LocalDate dueDate, int renewalCount, Boolean isReturned, Member member, LibraryBook libraryBook) {
        return new Checkout(loanDate, dueDate, renewalCount, isReturned, member, libraryBook);
    }

}
