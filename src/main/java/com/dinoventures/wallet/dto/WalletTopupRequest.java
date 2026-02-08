package com.dinoventures.wallet.dto;

public record WalletTopupRequest(
        Long userId,
        String assetType,
        Long amount
) {}
