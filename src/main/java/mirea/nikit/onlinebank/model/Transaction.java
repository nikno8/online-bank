package mirea.nikit.onlinebank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private Long fromId;
    private Long toId;
    private BigDecimal amount;
    private  LocalDateTime time;

    public Transaction(Long fromId, Long toId, BigDecimal amount, LocalDateTime time) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
        this.time = time;
    }
}
