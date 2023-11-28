package mirea.nikit.onlinebank.service;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.model.Transaction;
import mirea.nikit.onlinebank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsForAccount(Long id){
        return transactionRepository.findTransactionsByAccountId(id);
    }
    public void makeTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) throws Exception {
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            transactionRepository.save(new Transaction(amount,LocalDateTime.now(), "aborted", fromAccount));
            throw new Exception("Insufficient balance in the account");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountService.updateAccount(fromAccount);
        accountService.updateAccount(toAccount);
        transactionRepository.save(new Transaction(amount,LocalDateTime.now(), "debit success", fromAccount));
        transactionRepository.save(new Transaction(amount,LocalDateTime.now(), "receiving success", toAccount));
    }

}
