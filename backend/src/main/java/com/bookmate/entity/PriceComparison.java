package com.bookmate.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name = "price_comparisons") @Data @NoArgsConstructor @AllArgsConstructor
public class PriceComparison {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id", nullable = false) private Book book;
    @Column(nullable = false) private String platform;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal price;
    @CreationTimestamp @Column(name = "fetched_at") private LocalDateTime fetchedAt;
}
