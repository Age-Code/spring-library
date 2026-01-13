package spring.library.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Checkout;
import spring.library.domain.LibraryBook;
import spring.library.domain.Member;
import spring.library.dto.CheckoutDto;
import spring.library.repository.CheckoutRepository;
import spring.library.repository.LibraryBookRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CheckoutService {

    // Repository 선언
    private final MemberRepository memberRepository;
    private final LibraryBookRepository libraryBookRepository;
    private final CheckoutRepository checkoutRepository;

    // 생성
    public CheckoutDto.CreateResDto checkout(Long bookId, CheckoutDto.CreateReqDto createReqDto) {
        Member member = memberRepository.findById(createReqDto.getMemberId()).orElseThrow();
        LibraryBook libraryBook = libraryBookRepository.findById(bookId).orElseThrow();

        int count = checkoutRepository.countByMemberIdAndIsReturned(createReqDto.getMemberId(),false);

        if(libraryBook.getStatus().equals("대출중")){
            return null;
        }

        if(member.getFeature().equals("학생") && count>10){
            return null;
        }else if(member.getFeature().equals("교직원") && count>20){
            return null;
        }else if(member.getFeature().equals("관리자") && count>100){
            return null;
        }

        libraryBook.setStatus("대출중");
        Checkout checkout = checkoutRepository.save(createReqDto.toEntity(member, libraryBook));
        libraryBookRepository.save(libraryBook);
        return CheckoutDto.CreateResDto.builder().checkoutId(checkout.getId()).build();
    }

    // memberId로 조회
    public List<CheckoutDto.DetailResDto> findByIdAndReturn(Long memberId) {
        List<Checkout> checkoutList = checkoutRepository.findByMemberIdAndIsReturned(memberId, false).orElseThrow();
        return checkoutList.stream().map(CheckoutDto.DetailResDto::toDetailResDto).toList();
    }

    // 대출 전체 기록 조회
    public List<CheckoutDto.HistoryResDto> findById(Long memberId) {
        List<Checkout> checkoutList = checkoutRepository.findByMemberId(memberId).orElseThrow();
        return checkoutList.stream().map(CheckoutDto.HistoryResDto::toHistoryResDto).toList();
    }

    // 반납
    @Transactional
    public CheckoutDto.UpdateResDto returnBook(Long libraryBookId, CheckoutDto.UpdateReqDto updateReqDto) {
        Checkout checkout = checkoutRepository.findByLibraryBookIdAndMemberId(libraryBookId, updateReqDto.getMemberId()).orElseThrow();
        checkout.setIsReturned(true);
        checkout.getLibraryBook().setStatus("대출가능");
        return CheckoutDto.UpdateResDto.builder().checkoutId(checkout.getId()).build();
    }

    // 연장
    @Transactional
    public CheckoutDto.UpdateResDto renewal(Long libraryBookId, CheckoutDto.UpdateReqDto updateReqDto) {
        Checkout checkout = checkoutRepository.findByLibraryBookIdAndMemberId(libraryBookId, updateReqDto.getMemberId()).orElseThrow();

        if(checkout.getIsReturned() || checkout.getRenewalCount()>1 || !checkout.getDueDate().equals(LocalDate.now())) {
            return null;
        }
        checkout.setDueDate(checkout.getDueDate().plusDays(5));

        return CheckoutDto.UpdateResDto.builder().checkoutId(checkout.getId()).build();
    }
}
