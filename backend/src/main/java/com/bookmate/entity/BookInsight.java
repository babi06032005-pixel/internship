package com.bookmate.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
@Entity @Table(name = "book_insights") @Data @NoArgsConstructor @AllArgsConstructor
public class BookInsight {
    @Id @Column(name = "book_id") private Long bookId;
    @OneToOne(fetch = FetchType.LAZY) @MapsId @JoinColumn(name = "book_id") private Book book;
    @Column(length = 3000) private String summary;
    @Column(length = 1000) private String concepts;
    @Enumerated(EnumType.STRING) private DifficultyLevel difficulty;
    @Column(name = "recommended_for", length = 500) private String recommendedFor;
    @Column(name = "google_rating") private Double googleRating;
    @Column(name = "page_count") private Integer pageCount;
    @Column(name = "published_date") private String publishedDate;
    @CreationTimestamp @Column(name = "generated_at") private LocalDateTime generatedAt;
    @UpdateTimestamp @Column(name = "updated_at") private LocalDateTime updatedAt;
    public enum DifficultyLevel { BEGINNER, INTERMEDIATE, ADVANCED }
}
