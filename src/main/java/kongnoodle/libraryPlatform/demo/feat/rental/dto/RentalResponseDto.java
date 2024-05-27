package kongnoodle.libraryPlatform.demo.feat.rental.dto;

import java.time.LocalDateTime;

import kongnoodle.libraryPlatform.demo.feat.rental.entity.Rental;

public record RentalResponseDto(
	Long id,
	Long AccountId,
	LocalDateTime startDate,
	LocalDateTime endDate
) {
	public static RentalResponseDto from(Rental rental) {
		return new RentalResponseDto(
			rental.getId(),
			rental.getAccount().getId(),
			rental.getStartDate(),
			rental.getEndDate()
		);
	}
}
