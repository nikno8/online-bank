package mirea.nikit.onlinebank.repository;

import mirea.nikit.onlinebank.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
