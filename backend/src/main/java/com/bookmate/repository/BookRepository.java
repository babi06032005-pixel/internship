package com.bookmate.repository;
import com.bookmate.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByActiveTrueAndSoldFalse(Pageable pageable);
    Page<Book> findByCategoryAndActiveTrueAndSoldFalse(String category, Pageable pageable);
    @Query("SELECT b FROM Book b WHERE b.active=true AND b.sold=false AND (LOWER(b.title) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%',:q,'%')))")
    Page<Book> search(@Param("q") String query, Pageable pageable);
    List<Book> findBySeller_IdAndActiveTrue(Long sellerId);
}
