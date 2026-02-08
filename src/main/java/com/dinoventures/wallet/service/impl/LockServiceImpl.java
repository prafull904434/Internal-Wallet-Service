package com.dinoventures.wallet.service.impl;

import com.dinoventures.wallet.repository.WalletRepository;
import com.dinoventures.wallet.service.LockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LockServiceImpl implements LockService {

    private final WalletRepository walletRepository;

    public LockServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Long lockUserWallet(Long userId, String assetType) {
        return walletRepository
                .findByUserAndAssetForUpdate(userId, assetType)
                .orElseThrow(
                        () -> new RuntimeException("Wallet not found for user " + userId + " and asset " + assetType))
                .getId();
    }
}
