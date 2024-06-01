package kongnoodle.libraryPlatform.demo.feat.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom {
    @Id @GeneratedValue
    private Long id;

    private String bookTitle;
    private Long lenderId;
    private Long borrowerId;

    @Builder
    public ChatRoom(String bookTitle, Long LenderId, Long BorrowerId) {
        this.bookTitle = bookTitle;
        this.lenderId = LenderId;
        this.borrowerId = BorrowerId;
    }
}
