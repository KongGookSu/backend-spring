package kongnoodle.libraryPlatform.demo.feat.book.dto;

import kongnoodle.libraryPlatform.demo.feat.book.entity.Book;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalResponseDto;
import kongnoodle.libraryPlatform.demo.feat.rental.entity.RentalState;
import kongnoodle.libraryPlatform.demo.feat.user.dto.AccountResponseDto;

public record BookResponseDto(
	long id,
	BookInfoResponseDto bookInfoResponseDto,
	AccountResponseDto bookOwner,
	RentalResponseDto rentalResponseDto,
	String address,
	RentalState rentalState,
	int availableDays
) {
public static BookResponseDto from(Book book) {
		return new BookResponseDto(
			book.getId(),
			BookInfoResponseDto.from(book.getBookInfo()),
			AccountResponseDto.from(book.getAccount()),
			RentalResponseDto.from(book.getRental()),
			book.getAddress(),
			book.getRentalState(),
			book.getAvailableDays()
		);
	}
}
