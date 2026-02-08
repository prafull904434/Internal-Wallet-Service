package com.dinoventures.wallet.dto;

public record BonusRequest(
        Long userId,
        String assetType,
        Long amount,
        String reason
) {}
