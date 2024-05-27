package kongnoodle.libraryPlatform.demo.feat.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Long isbn;
	private String title;
	private String author;
	private String publisher;
	private String pubdate;
	private String imageUri;

	@Builder
	private BookInfo(Long isbn, String title, String author, String publisher, String pubdate, String imageUri) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.pubdate = pubdate;
		this.imageUri = imageUri;
	}
}
