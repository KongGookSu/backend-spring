package kongnoodle.libraryPlatform.demo.feat.chat.dto;

import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;

public record ChatRoomRequest(
    Long lenderId,
    String bookTitle
) {
    public ChatRoom toEntity(Long BorrowerId) {
        return ChatRoom.builder()
            .bookTitle(bookTitle)
            .LenderId(lenderId)
            .BorrowerId(BorrowerId)
            .build();
    }
}
