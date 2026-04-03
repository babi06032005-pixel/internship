package com.bookmate.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
@Entity @Table(name = "chat_messages") @Data @NoArgsConstructor @AllArgsConstructor
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "sender_id", nullable = false) private User sender;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "receiver_id", nullable = false) private User receiver;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id", nullable = false) private Book book;
    @Column(nullable = false, length = 2000) private String message;
    @Column(name = "is_read") private boolean read = false;
    @CreationTimestamp @Column(name = "timestamp", updatable = false) private LocalDateTime timestamp;
}
