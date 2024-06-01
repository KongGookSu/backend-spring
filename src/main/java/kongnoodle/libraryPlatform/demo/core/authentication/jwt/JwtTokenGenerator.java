package kongnoodle.libraryPlatform.demo.core.authentication.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {
	private final JwtTokenValidator jwtTokenValidator;

	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;  // 1일
	private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;  // 2주

	public Token createToken(String email) {
		long current = System.currentTimeMillis();
		Date accessExpireTime = new Date(current + ACCESS_TOKEN_EXPIRE_TIME);
		Date refreshExpireTime = new Date(current + REFRESH_TOKEN_EXPIRE_TIME);
		String accessToken = generateToken(email, accessExpireTime, Map.of("type", TokenType.ACCESS));
		String refreshToken = generateToken(email, refreshExpireTime, Map.of("type", TokenType.REFRESH));
		return Token.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	private String generateToken(String userId, Date expireTime, Map<String, Object> claims) {
		Key secretKey = jwtTokenValidator.SecretKey();

		return Jwts.builder()
			.setClaims(claims)
			.setSubject(userId)
			.setExpiration(expireTime)
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
	}

}