package kongnoodle.libraryPlatform.demo.feat.chat.repository;

import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLogJpaRepository extends JpaRepository<ChatLog, Long> {

    Page<ChatLog> findAllByChatRoomIdOrderByTimeDesc(Long chatRoomId, PageRequest pageRequest);
}
