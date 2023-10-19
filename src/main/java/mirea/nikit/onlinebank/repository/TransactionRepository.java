package mirea.nikit.onlinebank.repository;

import mirea.nikit.onlinebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
