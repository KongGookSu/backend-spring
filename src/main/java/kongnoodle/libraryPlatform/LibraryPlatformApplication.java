package kongnoodle.libraryPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LibraryPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryPlatformApplication.class, args);
	}

}
