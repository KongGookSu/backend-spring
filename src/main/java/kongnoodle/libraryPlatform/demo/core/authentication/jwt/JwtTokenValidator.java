package kongnoodle.libraryPlatform.demo.core.authentication.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kongnoodle.libraryPlatform.demo.feat.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {
	@Value("${jwt.secret}")
	private String secret;
	private static final String HEADER_PREFIX = "Bearer ";

	public String validateAccessToken(String rawToken){
		var token = parseHeader(rawToken);
		var userId = validateTokenType(token, TokenType.ACCESS);

		return userId;
	}

	public String validateRefreshToken(String rawToken) {
		var token = parseHeader(rawToken);
		var accountId = validateTokenType(token, TokenType.REFRESH);

		return accountId;
	}

	public String parseHeader(String header) {
		if (header == null || header.isEmpty()) {
			throw new IllegalArgumentException("Authorization 헤더가 없습니다.");
		} else if (!header.startsWith(HEADER_PREFIX)) {
			throw new IllegalArgumentException("Authorization 올바르지 않습니다.Bearer로 시작해야합니다.");
		} else if (header.split(" ").length != 2) {
			throw new IllegalArgumentException("Authorization 올바르지 않습니다.");
		}

		return header.split(" ")[1];
	}

	private String validateTokenType(String token, TokenType type) {
		Claims claims = extractClaims(token);
		if(!claims.get("type").equals(type.name())) {
			throw new JwtException("올바르지 않은 토큰 타입");
		}
		return claims.getSubject();
	}

	private Claims extractClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SecretKey()).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			throw new JwtException("토큰이 만료되었습니다.");
		}
	}

	protected Key SecretKey() {
		byte[] keyBytes = secret.getBytes();
		if (keyBytes.length < 32) {
			try {
				keyBytes = Arrays.copyOf(MessageDigest.getInstance("SHA-256").digest(keyBytes), 32);
			} catch (NoSuchAlgorithmException e) {
				throw new JwtException("JWT secret key 생성 실패");
			}
		}

		return Keys.hmacShaKeyFor(keyBytes);
	}
}