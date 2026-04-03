package com.bookmate.repository;
import com.bookmate.entity.BookInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface BookInsightRepository extends JpaRepository<BookInsight, Long> {
    Optional<BookInsight> findByBook_Id(Long bookId);
}
