package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Checkout> checkoutList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Purchase> purchaseList = new ArrayList<>();

    // 생성자
    protected Member() {}
    private Member(String name, Long idNumber, String feature, String email, String phoneNumber) {
        this.name = name;
        this.idNumber = idNumber;
        this.feature = feature;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public static Member of(String name, Long idNumber, String feature, String email, String phoneNumber) {
        return new Member(name, idNumber, feature, email, phoneNumber);
    }

}
