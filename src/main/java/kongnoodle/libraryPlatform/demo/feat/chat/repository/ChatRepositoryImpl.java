package kongnoodle.libraryPlatform.demo.feat.chat.repository;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatLog;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository{
    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatLogJpaRepository chatLogJpaRepository;

    @Override
    public ChatRoom getChatRoomReference(Long chatRoomId) {
        return chatRoomJpaRepository.getReferenceById(chatRoomId);
    }

    @Override
    public void saveChatLog(ChatLog chatLog) {
        chatLogJpaRepository.save(chatLog);
    }

    @Override
    public void saveChatRoom(ChatRoom chatRoom) {

    }

    @Override
    public Page<ChatLog> findChatLogByPage(Long chatRoomId, int page) {
        PageRequest pageRequest = PageRequest.of(page, 50);
        return chatLogJpaRepository.findAllByChatRoomIdOrderByTimeDesc(chatRoomId, pageRequest);
    }

    @Override
    public List<ChatRoom> findChatRoomByAccountId(Long accountId) {
        List<ChatRoom> chatRoomLender = chatRoomJpaRepository.findAllByLenderId(accountId);
        List<ChatRoom> chatRoomBorrower = chatRoomJpaRepository.findAllByBorrowerId(accountId);

        chatRoomLender.addAll(chatRoomBorrower);
        return chatRoomLender;
    }
}
