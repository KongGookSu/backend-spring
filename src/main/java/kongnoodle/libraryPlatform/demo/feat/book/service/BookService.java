package kongnoodle.libraryPlatform.demo.feat.book.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookInfoResponseDto;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookPostResponseDto;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookUpdateRequest;
import kongnoodle.libraryPlatform.demo.feat.book.dto.bookinfoxml.Item;
import kongnoodle.libraryPlatform.demo.feat.book.dto.bookinfoxml.Rss;
import kongnoodle.libraryPlatform.demo.feat.book.dto.enumeration.SearchOption;
import kongnoodle.libraryPlatform.demo.feat.book.repository.BookRepository;
import kongnoodle.libraryPlatform.demo.feat.book.entity.RentalState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kongnoodle.libraryPlatform.demo.feat.book.dto.BookCreateRequest;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookPost;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;
import kongnoodle.libraryPlatform.demo.feat.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	private final AccountRepository accountRepository;

	@Value("${naver.url}")
	private String naverURL;
	@Value("${naver.client.id}")
	private String clientId;
	@Value("${naver.client.secret}")
	private String clientSecret;

	@Transactional
	public void createBookByBookRequest(BookCreateRequest request, Long accountId) {
		Optional<BookInfo> bookInfo = bookRepository.findBookInfoByIsbn(request.isbn());
		if (bookInfo.isEmpty()) {
			BookInfo naverBookInfo = getBookInfoFromNaver(request.isbn());
			bookInfo = Optional.ofNullable(bookRepository.saveBookInfo(naverBookInfo));
		}

		BookPost bookPost = BookPost.builder()
			.address(request.address())
			.availableDays(request.availableRentalDays())
			.bookInfo(bookInfo.get())
//			.account(accountRepository.getReferenceById(accountId))
			.rentalState(RentalState.NONE)
			.build();

		bookRepository.saveBookPost(bookPost);
	}

	@Transactional(readOnly = true)
	public List<BookInfoResponseDto> searchBookInfo(SearchOption searchOption, String value) {
		return bookRepository.findBookInfoBySearchOption(searchOption, value).stream()
			.map(BookInfoResponseDto::from)
			.toList();
	}

	@Transactional(readOnly = true)
	public List<BookPostResponseDto> getBookPostByBookId(Long bookId) {
		return bookRepository.findBookPostByBookId(bookId).stream()
			.map(BookPostResponseDto::from)
			.toList();
	}

	@Transactional(readOnly = true)
	public List<BookPostResponseDto> getMyBookPost(Long accountId) {
		return bookRepository.findBookPostByAccountId(accountId).stream()
			.map(BookPostResponseDto::from)
			.toList();
	}

	@Transactional
	public void updateBookPost(Long bookPostId, BookUpdateRequest request, Long accountId) {
		BookPost bookPost = bookRepository.getBookPostById(bookPostId);
//		if (bookPost.getAccount().getId() != accountId) {
//			throw new IllegalArgumentException("해당 게시글에 대한 수정 권한이 없습니다.");
//		}

		bookPost.updateBookPost(request.address(), request.availableRentalDays());
	}

	@Transactional
	public void deleteBookById(Long bookPostId, Long accountId) {
		BookPost bookPost = bookRepository.getBookPostById(bookPostId);
//		if (bookPost.getAccount().getId() != accountId) {
//			throw new IllegalArgumentException("해당 게시글에 대한 삭제 권한이 없습니다.");
//		}

		bookRepository.deleteBookPostById(bookPostId);
	}


	private BookInfo getBookInfoFromNaver(String isbn) {
		WebClient webClient = WebClient.create();

		try {
			String response = webClient.get()
				.uri(naverURL + "?d_isbn=" + isbn)
				.header("X-Naver-Client-Id", clientId)
				.header("X-Naver-Client-Secret", clientSecret)
				.retrieve()
				.bodyToMono(String.class)
				.block();

			JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(response);
			Rss rss = (Rss) unmarshaller.unmarshal(reader);

			if (rss.getChannel().getItems().isEmpty()) {
				throw new IllegalArgumentException("입력한 ISBN에 대한 책이 없습니다.");
			}
			Item item = rss.getChannel().getItems().stream().findFirst().orElse(null);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


			return BookInfo.builder()
				.title(item.getTitle())
				.author(item.getAuthor())
				.publisher(item.getPublisher())
				.pubdate(LocalDate.parse(item.getPubdate(), formatter))
				.imageUri(item.getImage())
				.description(item.getDescription())
				.isbn(isbn)
				.build();

		} catch (JAXBException e) {
			throw new RuntimeException("XML 파싱과정에서 문제가 발생했습니다", e);
		}
	}

}
