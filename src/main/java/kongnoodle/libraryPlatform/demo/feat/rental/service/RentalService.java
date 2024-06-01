package kongnoodle.libraryPlatform.demo.feat.rental.service;

import java.time.LocalDateTime;
import java.util.List;
import kongnoodle.libraryPlatform.demo.feat.book.entity.RentalState;
import kongnoodle.libraryPlatform.demo.feat.book.repository.BookRepository;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalUpdateRequest;
import kongnoodle.libraryPlatform.demo.feat.user.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kongnoodle.libraryPlatform.demo.feat.book.entity.BookPost;
import kongnoodle.libraryPlatform.demo.feat.book.repository.BookPostJpaRepository;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalRequest;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalResponseDto;
import kongnoodle.libraryPlatform.demo.feat.rental.entity.Rental;
import kongnoodle.libraryPlatform.demo.feat.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepository rentalRepository;
	private final BookPostJpaRepository bookPostJpaRepository;
	private final BookRepository bookRepository;
	private final AccountRepository accountRepository;

	@Transactional(readOnly = true)
	public List<RentalResponseDto> findRentalById(Long accountId) {
		return rentalRepository.findByAccountId(accountId).stream()
			.map(RentalResponseDto::from)
			.toList();
	}

	@Transactional
	public void createRental(Long bookPostId, Long accountId) {
		BookPost bookPost = bookRepository.getBookPostById(bookPostId);

		if (bookPost.getRentalState() == RentalState.RENTAL) {
			throw new IllegalArgumentException("이미 대여된 책입니다.");
		}

		bookPost.setRentalState(RentalState.RENTAL);
		int availableDays = bookPost.getAvailableDays();

		Rental rental = Rental.builder()
			.startDate(LocalDateTime.now())
			.endDate(LocalDateTime.now().plusDays(availableDays))
			.account(accountRepository.getReferenceById(accountId))
			.build();

		rentalRepository.save(rental);
	}

	@Transactional
	public Long updateRentalByRentalRequest(Long rentalId, RentalUpdateRequest rentalRequest) {
		Rental rental = rentalRepository.findById(rentalId).orElseThrow(
			() -> new IllegalArgumentException("해당 대여가 존재하지 않습니다.")
		);
		rental.setStartDate(rentalRequest.startDate());
		rental.setEndDate(rentalRequest.endDate());
		return rentalRepository.save(rental).getId();
	}

	@Transactional
	public void deleteRentalById(Long rentalId) {
		rentalRepository.deleteById(rentalId);
	}
}
