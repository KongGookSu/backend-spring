package kongnoodle.libraryPlatform.demo.core.config;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kongnoodle.libraryPlatform.demo.core.annotations.TokenUserIdResolver;
import kongnoodle.libraryPlatform.demo.core.authentication.interceptor.TokenCheckInterceptor;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final TokenCheckInterceptor tokenCheckInterceptor;
	private final TokenUserIdResolver tokenUserIdResolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenCheckInterceptor)
			.addPathPatterns("/api/accounts")
			.excludePathPatterns("/api/v1/auth/signup"
				, "/api/v1/auth/login"
				, "/api/v1/exam-content/guest/**"
				, "/api/v1/auth/reissue"
				, "/api/v1/objection"
				, "/api/v1/objection/guest/**"
				, "/api/v1/exam/**"
				, "/api/v1/result/**/guest/**"
				, "/api/v1/course/exam-content/**/student/question"
			);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(tokenUserIdResolver);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
			.allowedHeaders("*");
	}
}