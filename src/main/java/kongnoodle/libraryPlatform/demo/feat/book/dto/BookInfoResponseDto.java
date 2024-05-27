package kongnoodle.libraryPlatform.demo.feat.book.dto;

import kongnoodle.libraryPlatform.demo.feat.book.entity.BookInfo;

public record BookInfoResponseDto(
	long id,
	long isbn,
	String title,
	String author,
	String publisher,
	String pubdate,
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
