package kongnoodle.libraryPlatform.demo.feat.rental.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalRequest;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalResponseDto;
import kongnoodle.libraryPlatform.demo.feat.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "대여", description = "대여 관련 API")
@RequiredArgsConstructor
public class rentalController {

	private final RentalService rentalService;

	@Operation(summary = "대여 조회", description = "단일 대여 정보를 조회한다.")
	@Parameter(in = ParameterIn.PATH, name = "rentalId", description = "대여 ID", required = true)
	@GetMapping("/api/rentals/{rentalId}")
	public RentalResponseDto getRental(@PathVariable Long rentalId) {
		return rentalService.findRentalById(rentalId);
	}

	@Operation(summary = "대여 등록", description = "유저의 대여를 등록하고, 대여 ID를 반환")
	@PostMapping("/api/rentals")
	public Long createRental(@RequestBody @Valid RentalRequest rentalrequest) {
		return rentalService.createRentalByRentalRequest(rentalrequest);
	}

	@Operation(summary = "대여 수정", description = "대여 정보를 수정하고, 수정된 대여 ID를 반환")
	@Parameter(in = ParameterIn.PATH, name = "rentalId", description = "대여 ID", required = true)
	@PutMapping("/api/rentals/{rentalId}")
	public Long updateRental(@PathVariable Long rentalId
			, @RequestBody @Valid RentalRequest rentalRequest) {
		return rentalService.updateRentalByRentalRequest(rentalId, rentalRequest);
	}

	@Operation(summary = "대여 삭제", description = "대여를 삭제한다.")
	@Parameter(in = ParameterIn.PATH, name = "rentalId", description = "대여 ID", required = true)
	@DeleteMapping("/api/rentals/{rentalId}")
	public void deleteRental(@PathVariable Long rentalId) {
		rentalService.deleteRentalById(rentalId);
	}
}
