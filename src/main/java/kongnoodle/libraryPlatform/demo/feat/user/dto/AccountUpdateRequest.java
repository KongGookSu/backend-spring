package kongnoodle.libraryPlatform.demo.feat.user.dto;

public record AccountUpdateRequest(
	long id,
	String email,
	String vendor,
	String nickname
) {
}
