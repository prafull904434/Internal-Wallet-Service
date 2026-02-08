package com.dinoventures.wallet.service.impl;

import com.dinoventures.wallet.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    private final LedgerService ledgerService;
    private final BalanceService balanceService;
    private final IdempotencyService idempotencyService;
    private final LockService lockService;
    private final AuditService auditService;

    public WalletServiceImpl(
            LedgerService ledgerService,
            BalanceService balanceService,
            IdempotencyService idempotencyService,
            LockService lockService,
            AuditService auditService) {
        this.ledgerService = ledgerService;
        this.balanceService = balanceService;
        this.idempotencyService = idempotencyService;
        this.lockService = lockService;
        this.auditService = auditService;
    }

    @Override
    @Transactional
    public void topup(Long userId, String assetType, Long amount, String idempotencyKey) {
        idempotencyService.validateOrThrow(idempotencyKey);

        Long walletId = lockService.lockUserWallet(userId, assetType);

        ledgerService.createCredit(walletId, amount, "TOPUP", idempotencyKey);

        // In a real system, you'd likely debit revenue or some external account here
        // too

        idempotencyService.markCompleted(idempotencyKey);
        auditService.record("TOPUP", userId, amount);
    }

    @Override
    @Transactional
    public void issueBonus(Long userId, String assetType, Long amount, String reason, String idempotencyKey) {
        idempotencyService.validateOrThrow(idempotencyKey);

        Long walletId = lockService.lockUserWallet(userId, assetType);

        ledgerService.createCredit(walletId, amount, "BONUS", reason);
        // Bonus usually comes from company funds/revenue
        // For simplicity/balance, let's assume it's just created or debited from
        // Revenue
        // But our Revenue method is credit only currently.
        // We'll just credit the user wallet for now.

        idempotencyService.markCompleted(idempotencyKey);
        auditService.record("BONUS", userId, amount);
    }

    @Override
    @Transactional
    public void spend(Long userId, String assetType, Long amount, String itemId, String idempotencyKey) {
        idempotencyService.validateOrThrow(idempotencyKey);

        Long walletId = lockService.lockUserWallet(userId, assetType);

        balanceService.ensureSufficientBalance(walletId, amount);

        ledgerService.createDebit(walletId, amount, "SPEND", itemId);
        ledgerService.createCreditToRevenue(amount);

        idempotencyService.markCompleted(idempotencyKey);
        auditService.record("SPEND", userId, amount);
    }

    @Override
    public Long getBalance(Long walletId) {
        return balanceService.getBalance(walletId);
    }
}
