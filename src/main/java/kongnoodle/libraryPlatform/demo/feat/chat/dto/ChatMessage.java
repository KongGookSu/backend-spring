package kongnoodle.libraryPlatform.demo.feat.chat.dto;

import java.time.LocalDateTime;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatLog;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;

public record ChatMessage(
    String message,
    Long senderId,
    String senderNickname,
    LocalDateTime time
) {
    public ChatLog toEntity(ChatRoom chatRoom) {
        return ChatLog.builder()
            .message(message)
            .senderId(senderId)
            .senderNickname(senderNickname)
            .time(time)
            .chatRoom(chatRoom)
            .build();
    }
}
