package kongnoodle.libraryPlatform.demo.feat.rental.dto;

import java.time.LocalDateTime;

public record RentalRequest(
	LocalDateTime startDate,
	LocalDateTime endDate,
	Long bookId
) {
}
