package kongnoodle.libraryPlatform.demo.feat.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kongnoodle.libraryPlatform.demo.feat.user.dto.AccountResponseDto;
import kongnoodle.libraryPlatform.demo.feat.user.dto.AccountUpdateRequest;
import kongnoodle.libraryPlatform.demo.feat.user.entity.Account;
import kongnoodle.libraryPlatform.demo.feat.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	@Transactional(readOnly = true)
	public AccountResponseDto findAccountById(Long id) {
		return accountRepository.findById(id).map(AccountResponseDto::from)
			.orElseThrow(
			() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다.")
		);
	}

	// email로 가입한 유저가 없다면 가입, 가입된 유저라면 정보 업데이트
	@Transactional
	public Long createAccount(Long userId,AccountUpdateRequest request) {
		Account account = accountRepository.findById(userId).orElse(
			Account.builder()
				.email(request.email())
				.vendor(request.vendor())
				.nickname(request.nickname())
				.build()
		);
		return accountRepository.save(account).getId();
	}

	@Transactional
	public Long createAccountByEmail(AccountUpdateRequest request) {
		Account account = accountRepository.findByEmail(request.email()).orElse(
			Account.builder()
				.email(request.email())
				.vendor(request.vendor())
				.nickname(request.nickname())
				.build()
		);
		return accountRepository.save(account).getId();
	}

	@Transactional
	public void updateAccount(Long userId ,AccountUpdateRequest request) {
		Account account = accountRepository.findById(userId).orElseThrow(
			() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다.")
		);
		account.setEmail(request.email());
		account.setVendor(request.vendor());
		account.setNickname(request.nickname());
		accountRepository.save(account);
	}

	@Transactional
	public void deleteAccount(Long userId) {
		Account account = accountRepository.findById(userId).orElseThrow(
			() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다.")
		);
		accountRepository.delete(account);
	}
}
