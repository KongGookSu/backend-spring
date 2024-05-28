
package kongnoodle.libraryPlatform.demo.feat.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kongnoodle.libraryPlatform.demo.feat.user.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
