package com.dinoventures.wallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue
    private Long id;

    private String action;
    private Long userId;
    private Long amount;
    private Instant timestamp = Instant.now();

    public AuditLog(String action, Long userId, Long amount) {
        this.action = action;
        this.userId = userId;
        this.amount = amount;
    }
}
