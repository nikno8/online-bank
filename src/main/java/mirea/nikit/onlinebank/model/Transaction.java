package mirea.nikit.onlinebank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime time;
    private String status;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonBackReference
    private Account account;
    public Transaction(BigDecimal amount, LocalDateTime time, String status, Account account) {
        this.amount = amount;
        this.time = time;
        this.status = status;
        this.account = account;
    }




}
