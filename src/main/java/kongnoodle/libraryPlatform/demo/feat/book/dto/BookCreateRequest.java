package kongnoodle.libraryPlatform.demo.feat.book.dto;

public record BookCreateRequest(
	String isbn,
	String address,
	int availableRentalDays,
	double latitude,
	double longitude
) {
}
