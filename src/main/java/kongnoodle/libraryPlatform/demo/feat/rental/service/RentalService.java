package kongnoodle.libraryPlatform.demo.feat.rental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kongnoodle.libraryPlatform.demo.feat.book.entity.Book;
import kongnoodle.libraryPlatform.demo.feat.book.repository.BookRepository;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalRequest;
import kongnoodle.libraryPlatform.demo.feat.rental.dto.RentalResponseDto;
import kongnoodle.libraryPlatform.demo.feat.rental.entity.Rental;
import kongnoodle.libraryPlatform.demo.feat.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepository rentalRepository;
	private final BookRepository bookRepository;

	@Transactional(readOnly = true)
	public RentalResponseDto findRentalById(Long rentalId) {
		Rental rental = rentalRepository.findById(rentalId).orElseThrow(
			() -> new IllegalArgumentException("해당 대여가 존재하지 않습니다.")
		);
		return RentalResponseDto.from(rental);
	}

	// Todo: Oauth2 완성 후 유저 정보 받아오기
	@Transactional
	public Long createRentalByRentalRequest(RentalRequest rentalRequest) {
		Book book = bookRepository.findById(rentalRequest.bookId()).orElseThrow(
			() -> new IllegalArgumentException("해당 책이 존재하지 않습니다.")
		);
		Rental rental = Rental.builder()
			.startDate(rentalRequest.startDate())
			.endDate(rentalRequest.endDate())
			.account(null)
			.book(book)
			.build();
		return rentalRepository.save(rental).getId();
	}

	@Transactional
	public Long updateRentalByRentalRequest(Long rentalId, RentalRequest rentalRequest) {
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
