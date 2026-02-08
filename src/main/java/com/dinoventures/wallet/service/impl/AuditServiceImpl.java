package com.dinoventures.wallet.service.impl;

import com.dinoventures.wallet.entity.AuditLog;
import com.dinoventures.wallet.repository.AuditRepository;
import com.dinoventures.wallet.service.AuditService;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void record(String action, Long userId, Long amount) {
        auditRepository.save(
                new AuditLog(action, userId, amount));
    }
}
