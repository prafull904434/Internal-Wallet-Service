package com.dinoventures.wallet.repository;

import com.dinoventures.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT w FROM Wallet w
        WHERE w.user.id = :userId
          AND w.asset.code = :assetCode
    """)
    Optional<Wallet> findByUserAndAssetForUpdate(
            @Param("userId") Long userId,
            @Param("assetCode") String assetCode
    );
}
