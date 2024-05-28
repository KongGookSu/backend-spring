package kongnoodle.libraryPlatform.demo.feat.rental.controller;

import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api")
public class RentalController {

	private final RentalService rentalService;

	@Operation(summary = "자신의 대여 목록 조회", description = "자신의 대여 목록을 조회한다.")
	@GetMapping("/my/rentals")
	public ResponseEntity<List<RentalResponseDto>> getRental() {
		//Todo: 로그인한 사용자의 ID를 가져오는 로직이 필요
		Long accountId = null;
		return ResponseEntity.ok(rentalService.findRentalById(accountId));
	}

	@Operation(summary = "대여하기", description = "유저의 대여를 등록")
	@PostMapping("/rentals")
	public ResponseEntity<Void> createRental(@RequestBody @Valid RentalRequest rentalrequest) {
		//Todo: 로그인한 사용자의 ID를 가져오는 로직이 필요
		Long accountId = null;
		rentalService.createRental(rentalrequest, accountId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "대여 수정", description = "대여 상태를 변경")
	@Parameter(in = ParameterIn.PATH, name = "rentalId", description = "대여 ID", required = true)
	@PutMapping("/rentals/{rentalId}")
	public Long updateRental(@PathVariable Long rentalId
			, @RequestBody @Valid RentalUpdateRequest rentalUpdateRequest) {
		return rentalService.updateRentalByRentalRequest(rentalId, rentalUpdateRequest);
	}

	@Operation(summary = "대여 삭제", description = "대여가 종료된 뒤 대여를 삭제한다.")
	@Parameter(in = ParameterIn.PATH, name = "rentalId", description = "대여 ID", required = true)
	@DeleteMapping("/rentals/{rentalId}")
	public void deleteRental(@PathVariable Long rentalId) {
		rentalService.deleteRentalById(rentalId);
	}
}
