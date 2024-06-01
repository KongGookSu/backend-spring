package kongnoodle.libraryPlatform.demo.feat.book.controller;

import java.util.List;

import kongnoodle.libraryPlatform.demo.core.annotations.TokenUserId;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookInfoResponseDto;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookPostResponseDto;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookUpdateRequest;
import kongnoodle.libraryPlatform.demo.feat.book.dto.enumeration.SearchOption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookCreateRequest;
import kongnoodle.libraryPlatform.demo.feat.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "책", description = "책 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
	private final BookService bookService;

	@Operation(summary = "사용자 책 게시글 등록", description = "유저의 책을 등록")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/book-post")
	public ResponseEntity<Void> createBook(@RequestBody @Valid BookCreateRequest request,
										   @TokenUserId Long accountId) {
		bookService.createBookByBookRequest(request, accountId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "책 정보 검색", description = "책 저자, 출판사, 제목을 바탕으로 목록을 검색한다")
	@Parameter(in = ParameterIn.PATH, name = "searchOption", description = "검색 옵션(TITLE, AUTHOR, PUBLISHER)", required = true)
	@Parameter(in = ParameterIn.QUERY, name = "value", description = "검색 값", required = true)
	@GetMapping("/book-info/{searchOption}")
	public ResponseEntity<List<BookInfoResponseDto>> searchBookInfo(@PathVariable SearchOption searchOption,
		@RequestParam String value) {
		return ResponseEntity.ok(bookService.searchBookInfo(searchOption, value));
	}

	@Operation(summary = "책 게시글 조회", description = "책에 해당하는 게시글을 조회한다.")
	@Parameter(in = ParameterIn.PATH, name = "bookId", description = "책 ID", required = true)
	@GetMapping("/book-info/{bookInfoId}/book-post")
	public ResponseEntity<List<BookPostResponseDto>> getBookPost(@PathVariable Long bookInfoId,
																 @TokenUserId Long userId) {
		return ResponseEntity.ok(bookService.getBookPostByBookId(bookInfoId, userId));
	}


	@GetMapping("/city/book-post")
	public ResponseEntity<List<BookPostResponseDto>> getBookPostByCity (@TokenUserId Long userId) {
		return ResponseEntity.ok(bookService.getBookPostByCity(userId));
	}

	@Operation(summary = "내 책 게시글 조회", description = "내가 등록한 책 게시글을 조회한다.")
	@GetMapping("/my/book-post")
	public ResponseEntity<List<BookPostResponseDto>> getMyBookPost(@TokenUserId Long userId) {
		return ResponseEntity.ok(bookService.getMyBookPost(userId));
	}

	@Operation(summary = "책 게시글 수정", description = "책 게시글 정보를 수정하고, 수정된 책 게시글 ID를 반환")
	@Parameter(in = ParameterIn.PATH, name = "bookPostId", description = "책 게시글 ID", required = true)
	@PutMapping("/book-post/{bookPostId}")
	public ResponseEntity<Void> updateBookPost(@PathVariable Long bookPostId,
											   @RequestBody @Valid BookUpdateRequest request,
											   @TokenUserId Long userId) {
		bookService.updateBookPost(bookPostId, request, userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Operation(summary = "책 게시글 삭제", description = "책을 삭제한다.")
	@Parameter(in = ParameterIn.PATH, name = "bookPostId", description = "책 게시글 ID", required = true)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/book-post/{bookPostId}")
	public ResponseEntity<Void> deleteBookPost(@PathVariable Long bookPostId,
											   @TokenUserId Long userId) {
		bookService.deleteBookById(bookPostId, userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
