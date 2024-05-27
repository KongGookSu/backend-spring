package kongnoodle.libraryPlatform.demo.oauth2;

public record GoogleTokenInfoModel(
	String access_token,
	int expires_in,
	String refresh_token,
	String scope,
	String token_type,
	String id_token
) {

}
