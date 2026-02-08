package Service.impl;

@Service
public class LockServiceImpl implements LockService {

    private final WalletRepository walletRepository;

    public LockServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Long lockUserWallet(Long userId, String assetType) {
        return walletRepository
                .findByUserAndAssetForUpdate(userId, assetType)
                .getId();
    }
}

