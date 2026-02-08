package Service.impl;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final LedgerRepository ledgerRepository;

    public BalanceServiceImpl(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public Long getBalance(Long walletId) {
        return ledgerRepository.sumByWalletId(walletId);
    }

    public void ensureSufficientBalance(Long walletId, Long amount) {
        if (getBalance(walletId) < amount) {
            throw new InsufficientBalanceException();
        }
    }
}

