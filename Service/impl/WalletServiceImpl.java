package Service.impl;

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
            AuditService auditService
    ) {
        this.ledgerService = ledgerService;
        this.balanceService = balanceService;
        this.idempotencyService = idempotencyService;
        this.lockService = lockService;
        this.auditService = auditService;
    }

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

}

