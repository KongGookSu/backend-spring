package kongnoodle.libraryPlatform.demo.feat.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private LocalDate pubdate;
	private String imageUri;
	private String description;

	@Builder
	private BookInfo(String isbn, String title, String author, String publisher, LocalDate pubdate, String imageUri, String description) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.pubdate = pubdate;
		this.imageUri = imageUri;
		this.description = description;
	}
}
