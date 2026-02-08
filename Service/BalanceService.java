package Service;

public interface BalanceService {

    Long getBalance(Long walletId);

    void ensureSufficientBalance(Long walletId, Long amount);
}

