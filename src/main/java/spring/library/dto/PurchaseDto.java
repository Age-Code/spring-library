package spring.library.dto;

import jakarta.validation.constraints.Null;
import lombok.*;
import spring.library.domain.Book;
import spring.library.domain.LibraryBook;
import spring.library.domain.Member;
import spring.library.domain.Purchase;

import java.time.LocalDate;

public class PurchaseDto {

    // Create Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        Long memberId;
        String title;
        String author;
        String publisher;
        int publicationYear;
        int purchaseRequestCount;

        public Book toBook() {
            return Book.of(title, author, publisher, publicationYear);
        }

        public Purchase toPurchase(Book book, Member member) {
            return Purchase.of(purchaseRequestCount, LocalDate.now(), "", "신청", book, member);
        }
    }

    // Create Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResDto {
        Long purchaseId;
    }

    // Detail Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DetailResDto {
        Long bookId;
        String title;
        String author;
        LocalDate requestDate;
        String dateOfProcess;
        String processResult;

        public static DetailResDto toDetailResDto(Purchase purchase) {
            return builder()
                    .bookId(purchase.getBook().getId())
                    .title(purchase.getBook().getTitle())
                    .author(purchase.getBook().getAuthor())
                    .requestDate(purchase.getRequestDate())
                    .dateOfProcess(purchase.getDateOfProcess())
                    .processResult(purchase.getProcessResult())
                    .build();
        }
    }

    // Update Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateReqDto {
        Long memberId;
        Long bookId;
        String title;
        String author;
        LocalDate requestDate;
        String dateOfProcess;
        String processResult;
    }

    // Update Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateResDto {
        Long purchaseId;
    }
}
