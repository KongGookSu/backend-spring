package kongnoodle.libraryPlatform.demo.feat.rental.dto;

import java.time.LocalDateTime;

public record RentalUpdateRequest(
    LocalDateTime startDate,
    LocalDateTime endDate
)
{}
