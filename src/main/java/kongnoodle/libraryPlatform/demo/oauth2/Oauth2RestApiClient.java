package kongnoodle.libraryPlatform.demo.oauth2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface Oauth2RestApiClient {
	@GetExchange("https://www.googleapis.com/oauth2/v3/userinfo")
	ResponseEntity<GoogleUserInfoModel> getGoogleUserInfo(
		@RequestHeader("Authorization") String accessToken
	);

	@PostExchange("https://oauth2.googleapis.com/token")
	ResponseEntity<GoogleTokenInfoModel> getGoogleTokenInfo(
		@RequestParam(value = "code") String code,
		@RequestParam(value = "client_id") String clientId,
		@RequestParam(value = "client_secret") String clientSecret,
		@RequestParam(value = "redirect_uri") String redirectUri,
		@RequestParam(value = "grant_type") String grantType
	);
}
