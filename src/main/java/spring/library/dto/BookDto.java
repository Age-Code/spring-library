package spring.library.dto;

import lombok.*;
import spring.library.domain.Book;
import spring.library.domain.LibraryBook;

public class BookDto {

    // Create Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        private String title;
        private String author;
        private String publisher;
        private int publicationYear;
        private String classification;
        private String status;
        private int amount;

        public Book toBook() {
            return Book.of(title, author, publisher, publicationYear);
        }

        public LibraryBook toLibraryBook(Book book) {
            return LibraryBook.of(classification, status, amount, book);
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
        private int publicationYear;
        private String classification;
        private String status;
        private int amount;

        public static DetailResDto toDetailResDto(LibraryBook libraryBook) {
            return builder()
                    .title(libraryBook.getBook().getTitle())
                    .author(libraryBook.getBook().getAuthor())
                    .publisher(libraryBook.getBook().getPublisher())
                    .publicationYear(libraryBook.getBook().getPublicationYear())
                    .classification(libraryBook.getClassification())
                    .status(libraryBook.getStatus())
                    .amount(libraryBook.getAmount())
                    .build();
        }
    }

    // Update Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateReqDto {
        String title;
        String author;
        String publisher;
        int publicationYear;
        String classification;
        String status;
        int amount;
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
