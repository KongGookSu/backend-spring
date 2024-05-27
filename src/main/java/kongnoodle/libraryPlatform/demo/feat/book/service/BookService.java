package kongnoodle.libraryPlatform.demo.feat.book.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kongnoodle.libraryPlatform.demo.feat.book.dto.BookRequest;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookResponseDto;
import kongnoodle.libraryPlatform.demo.feat.book.entity.Book;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;
import kongnoodle.libraryPlatform.demo.feat.book.repository.BookRepository;
import kongnoodle.libraryPlatform.demo.feat.rental.entity.RentalState;
import kongnoodle.libraryPlatform.demo.feat.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	private final AccountRepository accountRepository;

	@Transactional(readOnly = true)
	public BookResponseDto findBookById(Long bookId) {
		return bookRepository.findById(bookId).map(book -> BookResponseDto.from(book)).orElseThrow(
			() -> new IllegalArgumentException("해당 책이 존재하지 않습니다.")
		);
	}

	@Transactional
	public Long createBookByBookRequest(BookRequest request) {
		// Todo: Naver Book API를 통해 book info 가져오기
		Book book = Book.builder()
			.account(accountRepository.findById(request.OwnerId()).orElseThrow(
				() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
			))
			.bookInfo(BookInfo.builder()
				.isbn(1234567890L)
				.title("책 제목")
				.author("책 저자")
				.publisher("책 출판사")
				.pubdate("2021-01-01")
				.imageUri("https://image.uri")
				.build()
			)
			.rentalState(RentalState.valueOf(request.rentalState()))
			.address(request.address())
			.availableDays(request.availableRentalDays())
			.build();
		return bookRepository.save(book).getId();
	}

	@Transactional
	public Long updateBookByBookRequest(Long bookId, BookRequest request) {
		Book book = bookRepository.findById(bookId).orElseThrow(
			() -> new IllegalArgumentException("해당 책이 존재하지 않습니다.")
		);
		book.setAddress(request.address());
		book.setRentalState(RentalState.valueOf(request.rentalState()));
		book.setAvailableDays(request.availableRentalDays());
		book.setAccount(null);
		//TODO: 유저 sql.data 후 수정
		//TODO: ISBN이 수정되면 BookInfo도 수정 될 수 있는 로직 필요
		return bookRepository.save(book).getId();
	}

	@Transactional
	public void deleteBookById(Long bookId) {
		bookRepository.deleteById(bookId);
	}
}
