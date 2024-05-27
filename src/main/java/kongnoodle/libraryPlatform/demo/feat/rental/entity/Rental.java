package kongnoodle.libraryPlatform.demo.feat.rental.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import kongnoodle.libraryPlatform.demo.feat.book.entity.Book;
import kongnoodle.libraryPlatform.demo.feat.user.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Setter private LocalDateTime startDate;
	@Setter private LocalDateTime endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	@OneToOne(mappedBy = "rental")
	private Book book;

	@Builder
	public Rental(LocalDateTime startDate, LocalDateTime endDate, Account account, Book book) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.account = account;
		this.book = book;
	}
}
