package com.dinoventures.wallet.repository;

import com.dinoventures.wallet.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {

    Optional<TransactionRecord> findByIdempotencyKey(String idempotencyKey);
}
