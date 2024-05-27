package kongnoodle.libraryPlatform.demo.feat.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kongnoodle.libraryPlatform.demo.feat.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
