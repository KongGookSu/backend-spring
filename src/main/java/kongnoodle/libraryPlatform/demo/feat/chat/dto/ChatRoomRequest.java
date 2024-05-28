package kongnoodle.libraryPlatform.demo.feat.chat.dto;

import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;

public record ChatRoomRequest(
    Long LenderId,
    String bookTitle
) {
    public ChatRoom toEntity(Long BorrowerId) {
        return ChatRoom.builder()
            .bookTitle(bookTitle)
            .LenderId(LenderId)
            .BorrowerId(BorrowerId)
            .build();
    }
}
