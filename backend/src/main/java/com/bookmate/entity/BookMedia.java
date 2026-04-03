package com.bookmate.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
@Entity @Table(name = "book_media") @Data @NoArgsConstructor @AllArgsConstructor
public class BookMedia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id", nullable = false) private Book book;
    @Enumerated(EnumType.STRING) @Column(name = "media_type", nullable = false) private MediaType mediaType;
    @Column(name = "file_url", nullable = false) private String fileUrl;
    @Column(name = "file_name") private String fileName;
    @Column(name = "file_size") private Long fileSize;
    @CreationTimestamp @Column(name = "uploaded_at") private LocalDateTime uploadedAt;
    public enum MediaType { FRONT_IMAGE, BACK_IMAGE, INNER_PAGES, CONDITION_IMAGE, CONDITION_VIDEO }
}
