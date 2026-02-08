package com.dinoventures.wallet.dto;

public record SpendRequest(
        String assetType,
        Long amount,
        String itemId
) {}
