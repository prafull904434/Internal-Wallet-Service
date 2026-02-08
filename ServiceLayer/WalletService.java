package ServiceLayer;

public interface WalletService {

    void topup(
            Long userId,
            String assetType,
            Long amount,
            String idempotencyKey
    );

    void issueBonus(
            Long userId,
            String assetType,
            Long amount,
            String reason,
            String idempotencyKey
    );

    void spend(
            Long userId,
            String assetType,
            Long amount,
            String itemId,
            String idempotencyKey
    );

    Long getBalance(Long walletId);
}

