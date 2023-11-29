package mirea.nikit.onlinebank.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "savings_accounts")
@Data
public class SavingsAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    private BigDecimal goal;

    private BigDecimal balance;

    private boolean fullGoalRewardClaimed = false;


    public boolean isFullGoalRewardClaimed() {
        return fullGoalRewardClaimed;
    }
}
