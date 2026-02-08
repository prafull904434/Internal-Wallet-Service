package com.dinoventures.wallet.service.impl;

import com.dinoventures.wallet.entity.LedgerEntry;
import com.dinoventures.wallet.entity.Wallet;
import com.dinoventures.wallet.repository.LedgerRepository;
import com.dinoventures.wallet.repository.WalletRepository; // Needed to fetch wallet
import com.dinoventures.wallet.service.LedgerService;
import org.springframework.stereotype.Service;

@Service
public class LedgerServiceImpl implements LedgerService {

    private final LedgerRepository ledgerRepository;
    private final WalletRepository walletRepository;

    public LedgerServiceImpl(LedgerRepository ledgerRepository, WalletRepository walletRepository) {
        this.ledgerRepository = ledgerRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public void createDebit(Long walletId, Long amount, String type, String ref) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow();
        ledgerRepository.save(
                LedgerEntry.debit(wallet, amount, type, ref));
    }

    @Override
    public void createCredit(Long walletId, Long amount, String type, String ref) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow();
        ledgerRepository.save(
                LedgerEntry.credit(wallet, amount, type, ref));
    }

    @Override
    public void createCreditToRevenue(Long amount) {
        ledgerRepository.save(
                LedgerEntry.creditRevenue(amount));
    }
}
