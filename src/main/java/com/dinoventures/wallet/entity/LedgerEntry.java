package com.dinoventures.wallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Table(name = "ledger_entries")
@Data
@NoArgsConstructor
public class LedgerEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = true) 
    private Wallet wallet;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String entryType; 

    @Column(nullable = false)
    private String transactionType;

    private String reference;

    private Instant createdAt = Instant.now();

    public LedgerEntry(Wallet wallet, Long amount, String entryType, String transactionType, String reference) {
        this.wallet = wallet;
        this.amount = amount;
        this.entryType = entryType;
        this.transactionType = transactionType;
        this.reference = reference;
    }

    public static LedgerEntry debit(Wallet wallet, Long amount, String type, String ref) {
        return new LedgerEntry(wallet, amount, "DEBIT", type, ref);
    }

    public static LedgerEntry credit(Wallet wallet, Long amount, String type, String ref) {
        return new LedgerEntry(wallet, amount, "CREDIT", type, ref);
    }

    public static LedgerEntry creditRevenue(Long amount) {
        LedgerEntry entry = new LedgerEntry();
        entry.setAmount(amount);
        entry.setEntryType("CREDIT");
        entry.setTransactionType("REVENUE");
     
        return entry;
    }
}
