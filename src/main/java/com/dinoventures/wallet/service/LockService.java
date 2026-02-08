package com.dinoventures.wallet.service;

public interface LockService {
    Long lockUserWallet(Long userId, String assetType);
}
