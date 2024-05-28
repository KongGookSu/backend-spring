package kongnoodle.libraryPlatform.demo.feat.chat.repository;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatLog;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;
import org.springframework.data.domain.Page;

public interface ChatRepository {

    ChatRoom getChatRoomReference(Long chatRoomId);

    void saveChatLog(ChatLog chatLog);

    void saveChatRoom(ChatRoom chatRoom);

    Page<ChatLog> findChatLogByPage(Long chatRoomId, int page);

    List<ChatRoom> findChatRoomByAccountId(Long accountId);
}
