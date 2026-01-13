package spring.library.dto;

import lombok.*;
import spring.library.domain.Member;

public class MemberDto {

    // Create Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        String name;
        Long idNumber;
        String feature;
        String email;
        String phoneNumber;

        public Member toEntity() {
            return Member.of(name, idNumber, feature, email, phoneNumber);
        }
    }

    // Create Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResDto {
        Long memberId;
    }

    // Detail Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DetailResDto {
        Long memberId;
        String name;
        Long idNumber;
        String feature;
        String email;
        String phoneNumber;

        public static DetailResDto toDetailResDto(Member member) {
            return builder()
                    .memberId(member.getId())
                    .name(member.getName())
                    .idNumber(member.getIdNumber())
                    .feature(member.getFeature())
                    .email(member.getEmail())
                    .phoneNumber(member.getPhoneNumber())
                    .build();
        }
    }

    // Update Request DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateReqDto {
        String name;
        Long idNumber;
        String feature;
        String email;
        String phoneNumber;
    }

    // Update Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateResDto {
        Long memberId;
    }

    // Delete Response DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeleteResDto {
        Long memberId;
    }

}
