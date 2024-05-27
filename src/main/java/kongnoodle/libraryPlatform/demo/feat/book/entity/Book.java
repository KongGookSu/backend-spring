package kongnoodle.libraryPlatform.demo.feat.book.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import kongnoodle.libraryPlatform.demo.feat.rental.entity.Rental;
import kongnoodle.libraryPlatform.demo.feat.rental.entity.RentalState;
import kongnoodle.libraryPlatform.demo.feat.user.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne @Setter
	@JoinColumn(name = "account_id")
	private Account account;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_info_id")
	private BookInfo bookInfo;

	@Setter private String address;

	@Enumerated(EnumType.STRING)
	@Setter
	private RentalState rentalState;

	@Setter private int availableDays;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rental_id")
	@Setter private Rental rental;

	@Builder
	public Book(Account account, BookInfo bookInfo, String address, RentalState rentalState, int availableDays, Rental rental) {
		this.account = account;
		this.bookInfo = bookInfo;
		this.address = address;
		this.rentalState = rentalState;
		this.availableDays = availableDays;
		this.rental = rental;
	}
}
