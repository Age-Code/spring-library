package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Checkout;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Optional<List<Checkout>> findByMemberIdAndIsReturned(Long memberId, Boolean returned);
    Optional<List<Checkout>> findByMemberId(Long memberId);
    Optional<Checkout> findByLibraryBookIdAndMemberId(Long libraryBookId, Long memberId);
    int countByMemberIdAndIsReturned(Long memberId, Boolean returned);
}
