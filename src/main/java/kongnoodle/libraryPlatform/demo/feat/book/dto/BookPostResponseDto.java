package kongnoodle.libraryPlatform.demo.feat.book.dto;

import kongnoodle.libraryPlatform.demo.feat.book.entity.BookPost;
import kongnoodle.libraryPlatform.demo.feat.book.entity.RentalState;

public record BookPostResponseDto(
	long id,
	String address,
	RentalState rentalState,
	int availableDays
) {
 public static BookPostResponseDto from(BookPost bookPost) {
		return new BookPostResponseDto(
			bookPost.getId(),
			bookPost.getAddress(),
			bookPost.getRentalState(),
			bookPost.getAvailableDays()
		);
	}
}
