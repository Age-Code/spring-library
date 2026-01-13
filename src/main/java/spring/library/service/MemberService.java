package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.repository.MemberRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    // Repository 선언
    private final MemberRepository memberRepository;

    // 생성
    public MemberDto.CreateResDto create(MemberDto.CreateReqDto createReqDto) {
        Member member = memberRepository.save(createReqDto.toEntity());
        return MemberDto.CreateResDto.builder().memberId(member.getId()).build();
    }

    // 전체 조회
    public List<MemberDto.DetailResDto> findAll() {
        List<Member> memberDtoList = memberRepository.findAll();
        return memberDtoList.stream().map(MemberDto.DetailResDto::toDetailResDto).toList();
    }

    // 조회
    public MemberDto.DetailResDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return MemberDto.DetailResDto.toDetailResDto(member);
    }

    // 수정
    @Transactional
    public MemberDto.UpdateResDto update(Long memberId, MemberDto.UpdateReqDto updateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        if(!updateReqDto.getName().isBlank()){
            member.setName(updateReqDto.getName());
        }
        if(updateReqDto.getIdNumber() != null){
            member.setIdNumber(updateReqDto.getIdNumber());
        }
        if(updateReqDto.getFeature().isBlank()){
            member.setFeature(updateReqDto.getFeature());
        }
        if(updateReqDto.getEmail().isBlank()){
            member.setEmail(updateReqDto.getEmail());
        }
        if(updateReqDto.getPhoneNumber().isBlank()){
            member.setPhoneNumber(updateReqDto.getPhoneNumber());
        }

        return MemberDto.UpdateResDto.builder().memberId(member.getId()).build();
    }

    // 삭제
    public MemberDto.DeleteResDto delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        memberRepository.delete(member);
        return MemberDto.DeleteResDto.builder().memberId(member.getId()).build();
    }
}
