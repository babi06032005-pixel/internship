package com.bookmate.repository;
import com.bookmate.entity.BookMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookMediaRepository extends JpaRepository<BookMedia, Long> {
    List<BookMedia> findByBook_Id(Long bookId);
    void deleteByBook_Id(Long bookId);
}
