package com.dinoventures.wallet.repository;

import com.dinoventures.wallet.entity.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdempotencyRepository extends JpaRepository<IdempotencyKey, Long> {

    boolean existsByKey(String key);

    java.util.Optional<IdempotencyKey> findByKey(String key);
}
