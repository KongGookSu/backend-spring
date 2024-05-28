package kongnoodle.libraryPlatform.demo.feat.book.dto;

import java.time.LocalDate;
import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;

public record BookInfoResponseDto(
	long id,
	String isbn,
	String title,
	String author,
	String publisher,
	LocalDate pubdate,
	String imageUri
) {
	public static BookInfoResponseDto from(BookInfo bookInfo) {
		return new BookInfoResponseDto(
			bookInfo.getId(),
			bookInfo.getIsbn(),
			bookInfo.getTitle(),
			bookInfo.getAuthor(),
			bookInfo.getPublisher(),
			bookInfo.getPubdate(),
			bookInfo.getImageUri()
		);
	}
}
