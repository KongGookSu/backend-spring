package kongnoodle.libraryPlatform.demo.feat.book.repository;

import java.util.List;
import java.util.Optional;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoJpaRepository extends JpaRepository<BookInfo, Long> {
    Optional<BookInfo> findByIsbn(String isbn);

    List<BookInfo> findByTitleContaining(String value);

    List<BookInfo> findByAuthorContaining(String value);

    List<BookInfo> findByPublisher(String value);
}
