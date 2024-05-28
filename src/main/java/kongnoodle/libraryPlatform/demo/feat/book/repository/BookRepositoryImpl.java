package kongnoodle.libraryPlatform.demo.feat.book.repository;

import java.util.List;
import java.util.Optional;
import kongnoodle.libraryPlatform.demo.feat.book.dto.enumeration.SearchOption;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{
    private final BookPostJpaRepository bookPostJpaRepository;
    private final BookInfoJpaRepository bookInfoJpaRepository;

    @Override
    public Optional<BookInfo> findBookInfoByIsbn(String isbn) {
        return bookInfoJpaRepository.findByIsbn(isbn);
    }

    @Override
    public BookInfo saveBookInfo (BookInfo bookInfo) {
        return bookInfoJpaRepository.save(bookInfo);
    }

    @Override
    public BookPost saveBookPost(BookPost bookPost) {
        return bookPostJpaRepository.save(bookPost);
    }

    @Override
    public List<BookInfo> findBookInfoBySearchOption(SearchOption searchOption, String value) {
        if (searchOption == SearchOption.TITLE) {
            return bookInfoJpaRepository.findByTitleContaining(value);
        }

        if (searchOption == SearchOption.AUTHOR) {
            return bookInfoJpaRepository.findByAuthorContaining(value);
        }

        return bookInfoJpaRepository.findByPublisher(value);
    }

    @Override
    public List<BookPost> findBookPostByBookId(Long bookId) {
        return bookPostJpaRepository.findByBookInfoId(bookId);
    }

    @Override
    public List<BookPost> findBookPostByAccountId(Long accountId) {
        return bookPostJpaRepository.findByAccountId(accountId);
    }

    @Override
    public BookPost getBookPostById(Long bookPostId) {
        return bookPostJpaRepository.findById(bookPostId).orElseThrow(
            () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    @Override
    public void deleteBookPostById(Long bookPostId) {
        bookPostJpaRepository.deleteById(bookPostId);
    }
}
