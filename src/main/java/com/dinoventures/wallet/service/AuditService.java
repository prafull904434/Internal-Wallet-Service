package com.dinoventures.wallet.service;

public interface AuditService {
    void record(String action, Long userId, Long amount);
}
