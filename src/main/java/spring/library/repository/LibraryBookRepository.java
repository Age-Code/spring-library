package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.LibraryBook;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    Optional<List<LibraryBook>> findByBookId(Long bookId);
    void deleteByBookId(Long bookId);
}
