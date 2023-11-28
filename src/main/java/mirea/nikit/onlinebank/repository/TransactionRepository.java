package mirea.nikit.onlinebank.repository;

import mirea.nikit.onlinebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
     List<Transaction> findTransactionsByAccountId(Long id);
}
