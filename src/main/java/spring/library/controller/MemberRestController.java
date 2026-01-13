package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.MemberDto;
import spring.library.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberRestController {

    // MemberService 선언
    private final MemberService memberService;

    // 생성
    @PostMapping
    public MemberDto.CreateResDto create(@RequestBody MemberDto.CreateReqDto createReqDto) {
        return memberService.create(createReqDto);
    }

    // 전체 조회
    @GetMapping
    public List<MemberDto.DetailResDto> findAll() {
        return memberService.findAll();
    }

    // 수정
    @PutMapping("/{memberId}")
    public MemberDto.UpdateResDto update(@PathVariable Long memberId, @RequestBody MemberDto.UpdateReqDto updateReqDto) {
        return memberService.update(memberId, updateReqDto);
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public MemberDto.DeleteResDto delete(@PathVariable Long memberId) {
        return memberService.delete(memberId);
    }

}
