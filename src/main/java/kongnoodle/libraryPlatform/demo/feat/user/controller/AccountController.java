package kongnoodle.libraryPlatform.demo.feat.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kongnoodle.libraryPlatform.demo.core.annotations.TokenUserId;
import kongnoodle.libraryPlatform.demo.feat.user.dto.AccountResponseDto;
import kongnoodle.libraryPlatform.demo.feat.user.dto.AccountUpdateRequest;
import kongnoodle.libraryPlatform.demo.feat.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "계정", description = "계정 관련 API")
@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@Operation(summary = "계정 정보 조회", description = "유저 토큰으로 계정 정보 조회")
	@GetMapping("/api/accounts")
	public AccountResponseDto getAccount(
		@TokenUserId Long userId
	) {
		System.out.println("userId = " + userId);
		return accountService.findAccountById(userId);
	}

	@Operation(summary = "계정 정보 수정", description = "로그인 중인 유저의 계정 정보 수정")
	@PutMapping("/api/accounts")
	public void updateAccount(
		@RequestBody @Valid AccountUpdateRequest request,
		@TokenUserId Long userId
	) {
		accountService.updateAccount(userId,request);
	}

	@Operation(summary = "계정 삭제", description = "유저의 계정을 삭제하고 완료시 200 반환")
	@DeleteMapping("/api/accounts")
	public ResponseEntity<?> deleteAccount(
		@TokenUserId Long userId
	) {
		accountService.deleteAccount(userId);
		return ResponseEntity.ok().build();
	}
}
