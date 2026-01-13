package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    // Repository 선언
    private final BookRepository bookRepository;

    // 생성
    public BookDto.CreateResDto create(BookDto.CreateReqDto createReqDto) {
        Book book = bookRepository.save(createReqDto.toEntity());
        return BookDto.CreateResDto.builder().bookId(book.getId()).build();
    }

    // 전체 조회
    public List<BookDto.DetailResDto> findAll() {
        List<Book> bookDtoList = bookRepository.findAll();
        return bookDtoList.stream().map(BookDto.DetailResDto::toDetailResDto).toList();
    }

    // 조회
    public BookDto.DetailResDto findById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return BookDto.DetailResDto.toDetailResDto(book);
    }

    // 수정
    @Transactional
    public BookDto.UpdateResDto update(Long bookId, BookDto.UpdateReqDto updateReqDto) {
        Book book = bookRepository.findById(bookId).orElseThrow();

        if(!updateReqDto.getTitle().isBlank()){
            book.setTitle(updateReqDto.getTitle());
        }
        if(!updateReqDto.getAuthor().isBlank()){
            book.setAuthor(updateReqDto.getAuthor());
        }
        if(!updateReqDto.getPublisher().isBlank()){
            book.setPublisher(updateReqDto.getPublisher());
        }
        if(updateReqDto.getPublicationYear() != null){
            book.setPublicationYear(updateReqDto.getPublicationYear());
        }
        if(!updateReqDto.getClassification().isBlank()){
            book.setClassification(updateReqDto.getClassification());
        }
        if(!updateReqDto.getStatus().isBlank()){
            book.setStatus(updateReqDto.getStatus());
        }
        if(updateReqDto.getAmount() != null){
            book.setAuthor(updateReqDto.getAuthor());
        }

        return BookDto.UpdateResDto.builder().bookId(book.getId()).build();
    }

    // 삭제
    public BookDto.DeleteResDto delete(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(book);
        return BookDto.DeleteResDto.builder().bookId(book.getId()).build();
    }
}
