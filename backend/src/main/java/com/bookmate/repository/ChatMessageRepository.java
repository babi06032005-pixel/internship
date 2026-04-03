package com.bookmate.repository;
import com.bookmate.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT m FROM ChatMessage m WHERE (m.sender.id=:u1 AND m.receiver.id=:u2 AND m.book.id=:bookId) OR (m.sender.id=:u2 AND m.receiver.id=:u1 AND m.book.id=:bookId) ORDER BY m.timestamp ASC")
    List<ChatMessage> findConversation(@Param("u1") Long u1, @Param("u2") Long u2, @Param("bookId") Long bookId);
    long countByReceiver_IdAndReadFalse(Long receiverId);
}
