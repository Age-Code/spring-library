package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.BookDto;
import spring.library.dto.CheckoutDto;
import spring.library.dto.PurchaseDto;
import spring.library.service.BookService;
import spring.library.service.CheckoutService;
import spring.library.service.PurchaseService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookRestController {

    // Service 선언
    private final BookService bookService;
    private final CheckoutService checkoutService;
    private final PurchaseService purchaseService;

    // BookService
    // 생성
    @PostMapping
    public BookDto.CreateResDto create(@RequestBody BookDto.CreateReqDto createReqDto) {
        return bookService.create(createReqDto);
    }

    // 전체 조회
    @GetMapping
    public List<BookDto.DetailResDto> findAll() {
        return bookService.findAll();
    }

    // 수정
    @PutMapping("/{bookId}")
    public BookDto.UpdateResDto update(@PathVariable Long bookId, @RequestBody BookDto.UpdateReqDto updateReqDto) {
        return bookService.update(bookId, updateReqDto);
    }

    // 삭제
    @DeleteMapping("/{bookId}")
    public BookDto.DeleteResDto delete(@PathVariable Long bookId) {
        return bookService.delete(bookId);
    }

    // CheckoutService
    // 대출
    @PostMapping("/{bookId}/checkout")
    public CheckoutDto.CreateResDto checkout(@PathVariable Long bookId, @RequestBody CheckoutDto.CreateReqDto createReqDto) {
        return checkoutService.checkout(bookId, createReqDto);
    }

    // 현재 대출 조회
    @GetMapping("/checkout")
    public List<CheckoutDto.DetailResDto> findByIdAndReturn(@RequestParam("memberId") Long memberId) {
        return checkoutService.findByIdAndReturn(memberId);
    }

    // 대출 기록 조회
    @GetMapping("/history")
    public List<CheckoutDto.HistoryResDto> findById(@RequestParam("memberId") Long memberId) {
        return checkoutService.findById(memberId);
    }

    // 반납
    @PutMapping("/{bookId}/return")
    public CheckoutDto.UpdateResDto returnBook(@PathVariable Long bookId, @RequestBody CheckoutDto.UpdateReqDto updateReqDto) {
        return checkoutService.returnBook(bookId, updateReqDto);
    }

    // 연장
    @PutMapping("/{bookId}/renewal")
    public CheckoutDto.UpdateResDto renewal(@PathVariable Long bookId, @RequestBody CheckoutDto.UpdateReqDto updateReqDto) {
        return checkoutService.renewal(bookId, updateReqDto);
    }

    // PurchaseService
    // 구매 신청
    @PostMapping("/purchase-requests")
    public PurchaseDto.CreateResDto purchaseRequest(@RequestBody PurchaseDto.CreateReqDto createReqDto) {
        return purchaseService.purchaseRequest(createReqDto);
    }

    // 신청 조회
    @GetMapping("/purchase-requests")
    public List<PurchaseDto.DetailResDto> findByMember(@RequestParam("memberId") Long memberId) {
        return purchaseService.findByMember(memberId);
    }

    // 신청 처리
    @PutMapping("/purchase-requests")
    public PurchaseDto.UpdateResDto process(@RequestBody PurchaseDto.UpdateReqDto updateReqDto) {
        return purchaseService.process(updateReqDto);
    }
}
