package kongnoodle.libraryPlatform.demo.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		var info = new Info()
			.title("도서관")
			.version("V1")
			.description("glow 해커톤 API 문서입니다.");
		var bearerKeySecurityScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
			.description("JWT 인증 토큰을 입력해주세요.");
		var components = new Components().addSecuritySchemes("bearer-key", bearerKeySecurityScheme);
		var securityItem = new SecurityRequirement().addList("bearer-key");

		return new OpenAPI()
			.info(info)
			.components(components)
			.addSecurityItem(securityItem);
	}
}