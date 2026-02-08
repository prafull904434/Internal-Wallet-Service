package Service.impl;

@Service
public class LedgerServiceImpl implements LedgerService {

    private final LedgerRepository ledgerRepository;

    public LedgerServiceImpl(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public void createDebit(Long walletId, Long amount, String type, String ref) {
        ledgerRepository.save(
                LedgerEntry.debit(walletId, amount, type, ref)
        );
    }

    public void createCredit(Long walletId, Long amount, String type, String ref) {
        ledgerRepository.save(
                LedgerEntry.credit(walletId, amount, type, ref)
        );
    }

    public void createCreditToRevenue(Long amount) {
        ledgerRepository.save(
                LedgerEntry.creditRevenue(amount)
        );
    }
}

