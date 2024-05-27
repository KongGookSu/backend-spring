package kongnoodle.libraryPlatform.demo.feat.book.dto;

public record BookRequest(
	String isbn,
	Long OwnerId,
	String address,
	String rentalState,
	int availableRentalDays
) {

}
