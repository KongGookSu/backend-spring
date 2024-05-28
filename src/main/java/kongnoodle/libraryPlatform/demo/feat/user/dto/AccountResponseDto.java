package kongnoodle.libraryPlatform.demo.feat.user.dto;

import kongnoodle.libraryPlatform.demo.feat.user.entity.Account;

public record AccountResponseDto(
	long id,
	String email,
	String vendor,
	String nickname
) {
	public static AccountResponseDto from(Account account) {
		return new AccountResponseDto(
			account.getId(),
			account.getEmail(),
			account.getVendor(),
			account.getNickname()
		);
	}
}
