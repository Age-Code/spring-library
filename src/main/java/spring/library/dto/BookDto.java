package spring.library.dto;

import lombok.*;
import spring.library.domain.Book;

public class BookDto {

    // Create Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        private String title;
        private String author;
        private String publisher;
        private Long publicationYear;
        private String classification;
        private String status;
        private Long amount;

        public Book toEntity() {
            return Book.of(title, author, publisher, publicationYear, classification, status, amount);
        }
    }

    // Create Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResDto {
        Long bookId;
    }

    // Detail Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DetailResDto {
        private String title;
        private String author;
        private String publisher;
        private Long publicationYear;
        private String classification;
        private String status;
        private Long amount;

        public static DetailResDto toDetailResDto(Book book) {
            return builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .publicationYear(book.getPublicationYear())
                    .classification(book.getClassification())
                    .status(book.getStatus())
                    .amount(book.getAmount())
                    .build();
        }
    }

    // Update Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateReqDto {
        String title;
        String author;
        String publisher;
        Long publicationYear;
        String classification;
        String status;
        Long amount;
    }

    // Update Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateResDto {
        Long bookId;
    }

    // Delete Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeleteResDto {
        Long bookId;
    }

}
