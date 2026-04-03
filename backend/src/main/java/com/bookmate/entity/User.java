// ═══════════════════════════════════════════════════════
// FILE: src/main/java/com/bookmate/entity/User.java
// ═══════════════════════════════════════════════════════
package com.bookmate.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable = false) private String name;
    @Email @NotBlank @Column(nullable = false, unique = true) private String email;
    @NotBlank @Column(nullable = false) private String password;
    private String phone;
    private String location;
    @Column(name = "profile_picture") private String profilePicture;
    @Column(name = "is_active") private boolean active = true;
    @CreationTimestamp @Column(name = "created_at", updatable = false) private LocalDateTime createdAt;
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY) private List<Book> listedBooks;
}
