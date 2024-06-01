package kongnoodle.libraryPlatform.demo.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kongnoodle.libraryPlatform.demo.core.authentication.jwt.JwtTokenGenerator;
import kongnoodle.libraryPlatform.demo.core.authentication.jwt.Token;
import kongnoodle.libraryPlatform.demo.feat.user.dto.AccountUpdateRequest;
import kongnoodle.libraryPlatform.demo.feat.user.service.AccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Oauth2LoginController {

	private final Oauth2RestApiClient oauth2RestApiClient;
	private final JwtTokenGenerator jwtTokenGenerator;
	private final AccountService accountService;

	@Value("${oauth2.google-client-id}")
	private String googleClientId;
	@Value("${oauth2.google-client-secret}")
	private String googleClientSecret;

	@PostMapping("/login/oauth2/google")
	public ResponseEntity<Token> jwtTokenFromGoogleCode (
		@RequestParam("code") String code) {
		GoogleTokenInfoModel googleTokenInfoModel = oauth2RestApiClient.getGoogleTokenInfo(code, googleClientId, googleClientSecret,
			"http://localhost:8080/login/oauth/google", "authorization_code").getBody();
		GoogleUserInfoModel googleUserInfoModel =  oauth2RestApiClient.getGoogleUserInfo("Bearer " + googleTokenInfoModel.access_token()).getBody();
		Long id = accountService.createAccountByEmail(AccountUpdateRequest.of(googleUserInfoModel.email(), "google", googleUserInfoModel.name()));

		return ResponseEntity.ok(jwtTokenGenerator.createToken(id.toString()));
	}
	@GetMapping("/logi/oauth2/google/userInfo")
	public ResponseEntity<GoogleUserInfoModel> getGoogleUserInfo(
		@RequestParam("token") String accessToken) {
		return oauth2RestApiClient.getGoogleUserInfo("Bearer " + accessToken);
	}
}
