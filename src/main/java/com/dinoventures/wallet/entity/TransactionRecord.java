package com.dinoventures.wallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.Instant;

@Entity
@Table(
    name = "transactions",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"idempotency_key"}
    )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "idempotency_key")
    private String idempotencyKey;

    @Column(nullable = false)
    private String type;

    private Instant createdAt = Instant.now();
}
