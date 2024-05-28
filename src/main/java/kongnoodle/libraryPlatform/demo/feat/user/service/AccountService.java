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
	public AccountResponseDto findAccountById(Long accountId) {
		return accountRepository.findById(accountId).map(AccountResponseDto::from)
			.orElseThrow(
			() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다.")
		);
	}

	@Transactional
	public void updateAccount(AccountUpdateRequest request) {
		Account account = accountRepository.findById(request.id()).orElseThrow(
			() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다.")
		);
		account.setEmail(request.email());
		account.setVendor(request.vendor());
		account.setNickname(request.nickname());
		accountRepository.save(account);
	}
}
