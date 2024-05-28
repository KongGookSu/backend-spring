package kongnoodle.libraryPlatform.demo.feat.chat.dto;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;

public record ChatRoomDto(
    Long chatRoomId,
    String bookTitle
) {

    public static List<ChatRoomDto> of(List<ChatRoom> chatRoomList) {
        return chatRoomList.stream()
            .map(chatRoom -> new ChatRoomDto(
                chatRoom.getId(), chatRoom.getBookTitle()
            ))
            .toList();
    }
}
