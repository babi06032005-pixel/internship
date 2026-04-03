package com.bookmate.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "books")
@Data @NoArgsConstructor @AllArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable = false) private String title;
    @NotBlank @Column(nullable = false) private String author;
    @Column(nullable = false) private String category;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private BookCondition condition;
    @Enumerated(EnumType.STRING) @Column(name = "listing_type", nullable = false) private ListingType listingType;
    @NotNull @Min(0) @Column(nullable = false, precision = 10, scale = 2) private BigDecimal price;
    @Column(length = 2000) private String description;
    private String isbn;
    @Column(name = "is_sold") private boolean sold = false;
    @Column(name = "is_active") private boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "seller_id", nullable = false) private User seller;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY) private List<BookMedia> mediaFiles;
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY) private BookInsight insight;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY) private List<PriceComparison> priceComparisons;
    @CreationTimestamp @Column(name = "created_at", updatable = false) private LocalDateTime createdAt;
    public enum BookCondition { NEW, LIKE_NEW, GOOD, FAIR, POOR }
    public enum ListingType { NEW_BOOK, USED_BOOK }
}
