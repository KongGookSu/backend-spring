package kongnoodle.libraryPlatform.demo.feat.chat.repository;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllByLenderId(Long accountId);

    List<ChatRoom> findAllByBorrowerId(Long accountId);
}
