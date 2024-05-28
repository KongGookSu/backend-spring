package kongnoodle.libraryPlatform.demo.feat.rental.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kongnoodle.libraryPlatform.demo.feat.rental.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByAccountId(Long accountId);
}
