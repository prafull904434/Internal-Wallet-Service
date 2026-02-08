package com.dinoventures.wallet.service;

public interface IdempotencyService {
    void validateOrThrow(String key);
    void markCompleted(String key);
}
