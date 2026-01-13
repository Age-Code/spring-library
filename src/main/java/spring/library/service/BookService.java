package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.LibraryBook;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;
import spring.library.repository.LibraryBookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    // Repository 선언
    private final BookRepository bookRepository;
    private final LibraryBookRepository libraryBookRepository;

    // 생성
    public BookDto.CreateResDto create(BookDto.CreateReqDto createReqDto) {
        Book book = bookRepository.save(createReqDto.toBook());
        for(int i=0; i<createReqDto.getAmount(); i++) {
            libraryBookRepository.save(createReqDto.toLibraryBook(book));
        }
        return BookDto.CreateResDto.builder().bookId(book.getId()).build();
    }

    // 전체 조회
    public List<BookDto.DetailResDto> findAll() {
        List<LibraryBook> libraryBookList = libraryBookRepository.findAll();
        return libraryBookList.stream().map(BookDto.DetailResDto::toDetailResDto).toList();
    }

    // 수정
    @Transactional
    public BookDto.UpdateResDto update(Long bookId, BookDto.UpdateReqDto updateReqDto) {
        LibraryBook libraryBook = libraryBookRepository.findById(bookId).orElseThrow();
        Book book = libraryBook.getBook();
        List<LibraryBook> libraryBookList = book.getLibraryBookList();

        if(!updateReqDto.getTitle().isBlank()){
            book.setTitle(updateReqDto.getTitle());
        }
        if(!updateReqDto.getAuthor().isBlank()){
            book.setAuthor(updateReqDto.getAuthor());
        }
        if(!updateReqDto.getPublisher().isBlank()){
            book.setPublisher(updateReqDto.getPublisher());
        }
        if(updateReqDto.getPublicationYear() != 0){
            book.setPublicationYear(updateReqDto.getPublicationYear());
        }
        if(!updateReqDto.getClassification().isBlank()){
            libraryBook.setClassification(updateReqDto.getClassification());
        }
        if(!updateReqDto.getStatus().isBlank()){
            libraryBook.setStatus(updateReqDto.getStatus());
        }

        if(updateReqDto.getAmount() != 0) {
            int amount = libraryBookList.size() - updateReqDto.getAmount();
            int count = 0;
            if(amount > 0) {
                while(amount!=count){
                    count++;
                    libraryBookRepository.delete(libraryBookList.get(libraryBookList.size()-count));
                }
            }else{
                while(amount+count != 0){
                    count++;
                    libraryBookRepository.save(LibraryBook.of(updateReqDto.getClassification(),"대출가능", updateReqDto.getAmount(), book));
                }
            }
        }
        for(LibraryBook each : libraryBookList){
            each.setAmount(updateReqDto.getAmount());
        }

        return BookDto.UpdateResDto.builder().bookId(book.getId()).build();
    }

    // 삭제
    public BookDto.DeleteResDto delete(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(book);
        libraryBookRepository.deleteByBookId(bookId);
        return BookDto.DeleteResDto.builder().bookId(book.getId()).build();
    }
}
