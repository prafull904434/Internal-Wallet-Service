package com.dinoventures.wallet.service.impl;

import com.dinoventures.wallet.entity.IdempotencyKey;
import com.dinoventures.wallet.exception.DuplicateTransactionException;
import com.dinoventures.wallet.repository.IdempotencyRepository;
import com.dinoventures.wallet.service.IdempotencyService;
import org.springframework.stereotype.Service;

@Service
public class IdempotencyServiceImpl implements IdempotencyService {

    private final IdempotencyRepository repository;

    public IdempotencyServiceImpl(IdempotencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validateOrThrow(String key) {
        if (repository.existsByKey(key)) {
            throw new DuplicateTransactionException("Duplicate transaction key: " + key);
        }
        repository.save(new IdempotencyKey(key));
    }

    @Override
    public void markCompleted(String key) {
        repository.findByKey(key).ifPresent(idempotencyKey -> {
            idempotencyKey.setCompleted(true);
            repository.save(idempotencyKey);
        });
    }
}
