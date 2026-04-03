package com.bookmate.dto;
import com.bookmate.entity.Book;
import com.bookmate.entity.BookInsight;
import com.bookmate.entity.BookMedia;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Dto {
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class RegisterRequest {
        @NotBlank private String name;
        @Email @NotBlank private String email;
        @NotBlank @Size(min=6) private String password;
        private String phone; private String location;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class LoginRequest {
        @Email @NotBlank private String email;
        @NotBlank private String password;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class AuthResponse {
        private String token; private String type="Bearer";
        private Long userId; private String name; private String email;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UserResponse {
        private Long id; private String name; private String email;
        private String phone; private String location; private String profilePicture;
        private LocalDateTime createdAt;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookRequest {
        @NotBlank private String title; @NotBlank private String author;
        @NotBlank private String category; @NotNull private Book.BookCondition condition;
        @NotNull private Book.ListingType listingType;
        @NotNull @DecimalMin("0.0") private BigDecimal price;
        private String description; private String isbn;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookResponse {
        private Long id; private String title; private String author;
        private String category; private Book.BookCondition condition;
        private Book.ListingType listingType; private BigDecimal price;
        private String description; private String isbn; private boolean sold;
        private UserResponse seller; private List<BookMediaResponse> mediaFiles;
        private BookInsightResponse insight; private List<PriceComparisonResponse> priceComparisons;
        private LocalDateTime createdAt;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookMediaResponse {
        private Long id; private BookMedia.MediaType mediaType;
        private String fileUrl; private String fileName;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ChatRequest {
        @NotNull private Long senderId; @NotNull private Long receiverId;
        @NotNull private Long bookId; @NotBlank private String message;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ChatResponse {
        private Long id; private UserResponse sender; private UserResponse receiver;
        private Long bookId; private String bookTitle; private String message;
        private boolean read; private LocalDateTime timestamp;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class WsMessage {
        private Long senderId; private Long receiverId; private Long bookId;
        private String message; private String senderName; private LocalDateTime timestamp;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PriceComparisonResponse {
        private String platform; private BigDecimal price; private LocalDateTime fetchedAt;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PriceDashboardResponse {
        private BigDecimal sellerPrice; private List<PriceComparisonResponse> marketPrices;
        private BigDecimal averageMarketPrice; private BigDecimal savings; private double savingsPercent;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookInsightResponse {
        private String summary; private List<String> concepts;
        private BookInsight.DifficultyLevel difficulty; private String recommendedFor;
        private Double googleRating; private Integer pageCount; private String publishedDate;
        private LocalDateTime generatedAt;
    }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ChatbotRequest { @NotBlank private String query; private Long userId; }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ChatbotResponse { private String answer; private List<BookResponse> suggestedBooks; }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ApiResponse<T> { private boolean success; private String message; private T data; }
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PageResponse<T> {
        private List<T> content; private int page; private int size;
        private long totalElements; private int totalPages;
    }
}
