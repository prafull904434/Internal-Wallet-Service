package DTO;

public record WalletTopupRequest(
        Long userId,
        String assetType,
        Long amount
) {}

