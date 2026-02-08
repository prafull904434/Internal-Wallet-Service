package com.dinoventures.wallet.service.impl;

import com.dinoventures.wallet.exception.InsufficientBalanceException;
import com.dinoventures.wallet.repository.LedgerRepository;
import com.dinoventures.wallet.service.BalanceService;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final LedgerRepository ledgerRepository;

    public BalanceServiceImpl(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Override
    public Long getBalance(Long walletId) {
        Long balance = ledgerRepository.sumByWalletId(walletId);
        return balance != null ? balance : 0L;
    }

    @Override
    public void ensureSufficientBalance(Long walletId, Long amount) {
        if (getBalance(walletId) < amount) {
            throw new InsufficientBalanceException("Insufficient balance in wallet: " + walletId);
        }
    }
}
