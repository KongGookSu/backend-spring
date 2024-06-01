package kongnoodle.libraryPlatform.demo.feat.book.repository;

import java.util.List;
import java.util.Optional;
import kongnoodle.libraryPlatform.demo.feat.book.dto.enumeration.SearchOption;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookPost;

public interface BookRepository {

    Optional<BookInfo> findBookInfoByIsbn(String isbn);

    BookInfo saveBookInfo(BookInfo bookInfo);

    BookPost saveBookPost(BookPost bookPost);

    List<BookInfo> findBookInfoBySearchOption(SearchOption searchOption, String value);

    List<BookPost> findBookPostByBookId(Long bookId);

    List<BookPost> findBookPostByAccountId(Long accountId);

    BookPost getBookPostById(Long bookPostId);

    void deleteBookPostById(Long bookPostId);

    List<BookPost> findBookPostByCity(String city);
}
