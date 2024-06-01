package kongnoodle.libraryPlatform.demo.feat.book.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kongnoodle.libraryPlatform.demo.feat.book.entity.BookPost;

@Repository
public interface BookPostJpaRepository extends JpaRepository<BookPost, Long> {

    List<BookPost> findByBookInfoId(Long bookId);

    List<BookPost> findByAccountId(Long accountId);

    List<BookPost> findByCityContaining(String city);
}
