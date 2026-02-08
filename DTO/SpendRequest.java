package DTO;

public record SpendRequest(
        String assetType,
        Long amount,
        String itemId
) {}

