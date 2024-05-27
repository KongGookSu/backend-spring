package kongnoodle.libraryPlatform.demo.feat.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kongnoodle.libraryPlatform.demo.feat.rental.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
