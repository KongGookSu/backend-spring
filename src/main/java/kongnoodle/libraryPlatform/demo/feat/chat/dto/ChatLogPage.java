package kongnoodle.libraryPlatform.demo.feat.chat.dto;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatLog;
import org.springframework.data.domain.Page;

public record ChatLogPage(
    boolean hasNext,
    List<ChatMessage> chattingList
) {

    public static ChatLogPage of(Page<ChatLog> chatLogPage) {
        List<ChatMessage> chatMessageList = chatLogPage.getContent().stream()
                .map(chatLog -> new ChatMessage(
                    chatLog.getMessage(), chatLog.getSenderId(),
                    chatLog.getSenderNickname(), chatLog.getTime()
                ))
                .toList();

        return new ChatLogPage(chatLogPage.hasNext(), chatMessageList);
    }
}
