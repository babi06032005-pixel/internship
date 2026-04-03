package com.bookmate.repository;
import com.bookmate.entity.PriceComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PriceComparisonRepository extends JpaRepository<PriceComparison, Long> {
    List<PriceComparison> findByBook_IdOrderByPriceAsc(Long bookId);
    void deleteByBook_Id(Long bookId);
}
