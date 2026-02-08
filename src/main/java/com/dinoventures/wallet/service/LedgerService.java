package com.dinoventures.wallet.service;

public interface LedgerService {
    void createCredit(Long walletId, Long amount, String type, String reference);
    void createDebit(Long walletId, Long amount, String type, String reference);
    void createCreditToRevenue(Long amount);
}
