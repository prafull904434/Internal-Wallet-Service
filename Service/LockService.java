package Service;

public interface LockService {

    Long lockUserWallet(Long userId, String assetType);
}

