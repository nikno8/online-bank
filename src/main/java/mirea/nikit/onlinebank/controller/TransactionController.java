package mirea.nikit.onlinebank.controller;

import mirea.nikit.onlinebank.model.Transaction;
import mirea.nikit.onlinebank.model.dto.TransferRequest;
import mirea.nikit.onlinebank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping
    public List <Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> makeTransfer(@RequestBody TransferRequest transferRequest) {
        try {
            transactionService.makeTransfer(transferRequest.getFromAccountId(), transferRequest.getToAccountId(), transferRequest.getAmount());
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transfer failed: " + e.getMessage());
        }
    }
}
