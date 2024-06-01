package kongnoodle.libraryPlatform.demo.core.authentication.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kongnoodle.libraryPlatform.demo.core.authentication.jwt.JwtTokenValidator;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class TokenCheckInterceptor implements HandlerInterceptor {
	private final JwtTokenValidator jwtTokenValidator;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if(request.getMethod().equals(HttpMethod.OPTIONS.name())) {
			return true;
		}
		String rawToken = request.getHeader("Authorization");

		request.setAttribute("userId", jwtTokenValidator.validateAccessToken(rawToken));

		return true;
	}

}