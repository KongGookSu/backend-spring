package kongnoodle.libraryPlatform.demo.feat.chat;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatLogPage;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatMessage;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatRoomDto;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatRoomRequest;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatLog;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;
import kongnoodle.libraryPlatform.demo.feat.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @Async
    @Transactional
    public void saveChat(ChatMessage chatMessage, Long chatRoomId) {
        ChatRoom chatRoom = chatRepository.getChatRoomReference(chatRoomId);

        chatRepository.saveChatLog(chatMessage.toEntity(chatRoom));
    }

    public void createChatRoom(ChatRoomRequest chatRoomRequest, Long accountId) {
        ChatRoom chatRoom = chatRoomRequest.toEntity(accountId);
        
        chatRepository.saveChatRoom(chatRoom);
    }

    public ChatLogPage getChatLog(Long chatRoomId, int page) {
        Page<ChatLog> chatLogPage = chatRepository.findChatLogByPage(chatRoomId, page);

        return ChatLogPage.of(chatLogPage);
    }

    @Transactional(readOnly = true)
    public List<ChatRoomDto> getMyChatRoom(Long accountId) {
        List<ChatRoom> chatRoomList = chatRepository.findChatRoomByAccountId(accountId);

        return ChatRoomDto.of(chatRoomList);
    }
}
