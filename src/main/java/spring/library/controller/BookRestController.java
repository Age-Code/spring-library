package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookRestController {

    // BookService 선언
    private final BookService bookService;

    // 생성
    @PostMapping
    public BookDto.CreateResDto createBook(@RequestBody BookDto.CreateReqDto createReqDto) {
        return bookService.create(createReqDto);
    }

    // 전체 조회
    @GetMapping
    public List<BookDto.DetailResDto> findAll() {
        return bookService.findAll();
    }

    // 조회
    @GetMapping("/{bookId}")
    public BookDto.DetailResDto findById(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    // 수정
    @PutMapping("/{bookId}")
    public BookDto.UpdateResDto update(@PathVariable Long bookId, @RequestBody BookDto.UpdateReqDto updateReqDto) {
        return bookService.update(bookId, updateReqDto);
    }

    // 삭제
    @DeleteMapping("/{bookId}")
    public BookDto.DeleteResDto deleteBook(@PathVariable Long bookId) {
        return bookService.delete(bookId);
    }

}
