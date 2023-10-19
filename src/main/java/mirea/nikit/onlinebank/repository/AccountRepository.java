package mirea.nikit.onlinebank.repository;

import mirea.nikit.onlinebank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
