package com.dinoventures.wallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "idempotency_keys",
    uniqueConstraints = @UniqueConstraint(columnNames = {"key_value"})
)
@Data
@NoArgsConstructor
public class IdempotencyKey {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "key_value")
    private String key;

    private boolean completed = false;

    public IdempotencyKey(String key) {
        this.key = key;
    }
}
