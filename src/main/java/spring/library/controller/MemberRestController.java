package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping
    public MemberDto.CreateResDto createMember(@RequestBody MemberDto.CreateReqDto createReqDto) {
        return memberService.createMember(createReqDto);
    }

    @GetMapping
    public List<MemberDto.DetailResDto> findAll() {
        return memberService.findAll();
    }

    @PutMapping("/{memberId}")
    public MemberDto.UpdateResDto update(@PathVariable Long memberId, @RequestBody MemberDto.UpdateReqDto updateReqDto) {
        return memberService.update(id, updateReqDto);
    }

    @DeleteMapping("/{memberId}")
    public
}
