package kongnoodle.libraryPlatform.demo.feat.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatLog {
    @Id @GeneratedValue
    private Long id;

    private String message;
    private Long senderId;
    private String senderNickname;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Builder
    public ChatLog(String message, Long senderId, String senderNickname, LocalDateTime time, ChatRoom chatRoom) {
        this.message = message;
        this.senderId = senderId;
        this.senderNickname = senderNickname;
        this.time = time;
        this.chatRoom = chatRoom;
    }
}
