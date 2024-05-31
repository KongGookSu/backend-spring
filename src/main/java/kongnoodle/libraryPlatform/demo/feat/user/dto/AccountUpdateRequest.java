package kongnoodle.libraryPlatform.demo.feat.user.dto;

import kongnoodle.libraryPlatform.demo.feat.user.entity.Account;

public record AccountUpdateRequest(
	long id,
	String email,
	String vendor,
	String nickname
) {
	public static AccountUpdateRequest of (String email, String vendor, String nickname) {
		return new AccountUpdateRequest(0, email, vendor, nickname);
	}
}
