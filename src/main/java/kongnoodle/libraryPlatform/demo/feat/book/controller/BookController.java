package kongnoodle.libraryPlatform.demo.feat.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookRequest;
import kongnoodle.libraryPlatform.demo.feat.book.dto.BookResponseDto;
import kongnoodle.libraryPlatform.demo.feat.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "책", description = "책 관련 API")
@RestController
@RequiredArgsConstructor
public class BookController {
	private final BookService bookService;

	@Operation(summary = "단일 책 조회", description = "단일 책 정보와 대여 정보 함께 조회한다.")
	@Parameter(in = ParameterIn.PATH, name = "bookId", description = "책 ID", required = true)
	@GetMapping("/api/books/{bookId}")
	public ResponseEntity<BookResponseDto> getBook(@PathVariable Long bookId) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(bookId));
	}

	@Operation(summary = "책 등록", description = "유저의 책을 등록하고, 책 ID를 반환")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/books")
	public Long createBook(@RequestBody @Valid BookRequest request) {
		return bookService.createBookByBookRequest(request);
	}

	@Operation(summary = "책 수정", description = "책 정보를 수정하고, 수정된 책 ID를 반환")
	@Parameter(in = ParameterIn.PATH, name = "bookId", description = "책 ID", required = true)
	@PutMapping("/api/books/{bookId}")
	public Long updateBook(@PathVariable Long bookId, @RequestBody @Valid BookRequest request) {
		return bookService.updateBookByBookRequest(bookId, request);
	}

	@Operation(summary = "책 삭제", description = "책을 삭제한다.")
	@Parameter(in = ParameterIn.PATH, name = "bookId", description = "책 ID", required = true)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/api/books/{bookId}")
	public void deleteBook(@PathVariable Long bookId) {
		bookService.deleteBookById(bookId);
	}
}
