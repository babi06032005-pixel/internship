package com.bookmate.repository;
import com.bookmate.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Put each in its own file in real project; combined here for reference

@Repository
interface _UserRepository extends JpaRepository<com.bookmate.entity.User, Long> {
    Optional<com.bookmate.entity.User> findByEmail(String email);
    boolean existsByEmail(String email);
}
