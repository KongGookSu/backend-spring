package kongnoodle.libraryPlatform.demo.oauth2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Oauth2LoginController {

	private final Oauth2RestApiClient oauth2RestApiClient;

	@GetMapping("/login/test/oauth2/google")
	public ResponseEntity<GoogleTokenInfoModel> getGoogleTokenInfo(
		@RequestParam("code") String code) {
		return oauth2RestApiClient.getGoogleTokenInfo(code, "clinet-id","clinet-secret", "http://localhost:8080/login/oauth/google","authorization_code");
	}

	@GetMapping("/login/test/oauth2/google/userInfo")
	public ResponseEntity<GoogleUserInfoModel> getGoogleUserInfo(
		@RequestParam("token") String accessToken) {
		return oauth2RestApiClient.getGoogleUserInfo("Bearer " + accessToken);
	}
}
