package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.domain.Purchase;
import spring.library.dto.PurchaseDto;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    // Repository 선언
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;

    // 생성
    public PurchaseDto.CreateResDto purchaseRequest(PurchaseDto.CreateReqDto createReqDto) {
        Member member = memberRepository.findById(createReqDto.getMemberId()).orElseThrow();
        Book book = bookRepository.save(createReqDto.toBook());

        Purchase purchase = purchaseRepository.save(createReqDto.toPurchase(book, member));
        return PurchaseDto.CreateResDto.builder().purchaseId(purchase.getId()).build();
    }

    // 전체 조회
    public List<PurchaseDto.DetailResDto> findByMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Purchase> purchaseList = new ArrayList<>();
        switch(member.getFeature()){
            case "관리자" -> purchaseList = purchaseRepository.findAll();
            default -> purchaseList = member.getPurchaseList();
        }
        return purchaseList.stream().map(PurchaseDto.DetailResDto::toDetailResDto).toList();
    }

    // 수정
    @Transactional
    public PurchaseDto.UpdateResDto process(PurchaseDto.UpdateReqDto updateReqDto) {
        Member member = memberRepository.findById(updateReqDto.getMemberId()).orElseThrow();
        Purchase purchase = purchaseRepository.findByBookId(updateReqDto.getBookId()).orElseThrow();

        if(!member.getFeature().equals("관리자")){
            return null;
        }

        if(!updateReqDto.getDateOfProcess().isBlank()){
            purchase.setDateOfProcess(updateReqDto.getDateOfProcess());
        }
        if(!updateReqDto.getProcessResult().isBlank()){
            purchase.setProcessResult(updateReqDto.getProcessResult());
        }

        return PurchaseDto.UpdateResDto.builder().purchaseId(purchase.getId()).build();
    }

}
