package DTO;

public record BonusRequest(
        Long userId,
        String assetType,
        Long amount,
        String reason
) {}

