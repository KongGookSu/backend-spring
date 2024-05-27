package kongnoodle.libraryPlatform.demo.oauth2;


public record GoogleUserInfoModel(
	String sub,
	String name,
	String given_name,
	String family_name,
	String picture,
	String email,
	boolean email_verified,
	String locale
) {
}
