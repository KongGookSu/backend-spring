package kongnoodle.libraryPlatform.demo.feat.chat;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatLogPage;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatMessage;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatRoomDto;
import kongnoodle.libraryPlatform.demo.feat.chat.dto.ChatRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @PostMapping("/api/chat-room")
    public ResponseEntity<Void> createChatRoom(ChatRoomRequest chatRoomRequest) {
        //TODO: 자신의 ID를 받아와야함
        Long accountId = null;
        chatService.createChatRoom(chatRoomRequest, accountId);
        return ResponseEntity.ok().build();
    }

    @MessageMapping("/channel/{channelId}")
    public void sendMessage(ChatMessage chatMessage, @DestinationVariable Long chatRoomId) {
        chatService.saveChat(chatMessage, chatRoomId);
        messagingTemplate.convertAndSend("/sub/chat-room/" + chatRoomId
            , chatMessage);
    }

    @GetMapping("/api/chat-room/{chatRoomId}/chatLog")
    public ResponseEntity<ChatLogPage> getChatLog(@PathVariable Long chatRoomId,
                                                    @RequestParam int page) {
        return ResponseEntity.ok(chatService.getChatLog(chatRoomId, page));
    }

    @GetMapping("/api/my/chat-room")
    public ResponseEntity<List<ChatRoomDto>> getMyChatRoom() {
        //TODO: 자신의 ID를 받아와야함
        Long accountId = null;
        return ResponseEntity.ok(chatService.getMyChatRoom(accountId));
    }
}
