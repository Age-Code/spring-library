package spring.library.dto;

import lombok.*;
import spring.library.domain.Book;
import spring.library.domain.Checkout;
import spring.library.domain.LibraryBook;
import spring.library.domain.Member;

import java.time.LocalDate;

public class CheckoutDto {

    // Create Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        Long memberId;

        public Checkout toEntity(Member member, LibraryBook libraryBook) {
            LocalDate dueDate = LocalDate.now();
            switch (member.getFeature()) {
                case "학생" -> dueDate = LocalDate.now().plusDays(10);
                case "교직원" -> dueDate = LocalDate.now().plusDays(30);
                case "관리자" -> dueDate = LocalDate.now().plusDays(110813);
            }
            return Checkout.of(LocalDate.now(), dueDate, 0, false, member, libraryBook);
        }
    }

    // Create Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResDto {
        Long checkoutId;
    }

    // Detail Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DetailResDto {
        Long bookId;
        String title;
        String author;
        LocalDate loanDate;
        LocalDate dueDate;
        int renewalCount;

        public static DetailResDto toDetailResDto(Checkout checkout) {
            return builder()
                    .bookId(checkout.getLibraryBook().getId())
                    .title(checkout.getLibraryBook().getBook().getTitle())
                    .author(checkout.getLibraryBook().getBook().getAuthor())
                    .loanDate(checkout.getLoanDate())
                    .dueDate(checkout.getDueDate())
                    .renewalCount(checkout.getRenewalCount())
                    .build();
        }
    }

    // History Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class HistoryResDto {
        Long bookId;
        String title;
        String author;
        LocalDate loanDate;
        LocalDate dueDate;
        int renewalCount;
        Boolean isReturned;

        public static HistoryResDto toHistoryResDto(Checkout checkout) {
            return builder()
                    .bookId(checkout.getLibraryBook().getId())
                    .title(checkout.getLibraryBook().getBook().getTitle())
                    .author(checkout.getLibraryBook().getBook().getAuthor())
                    .loanDate(checkout.getLoanDate())
                    .dueDate(checkout.getDueDate())
                    .renewalCount(checkout.getRenewalCount())
                    .isReturned(checkout.getIsReturned())
                    .build();
        }
    }

    // Update Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateReqDto {
        Long memberId;
    }

    // Update Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateResDto {
        Long checkoutId;
    }

    // Delete Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeleteResDto {
        Long checkoutId;
    }

}
