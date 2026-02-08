package com.dinoventures.wallet.repository;

import com.dinoventures.wallet.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    @Query("""
        SELECT COALESCE(SUM(
            CASE
                WHEN l.entryType = 'CREDIT' THEN l.amount
                ELSE -l.amount
            END
        ), 0)
        FROM LedgerEntry l
        WHERE l.wallet.id = :walletId
    """)
    Long sumByWalletId(@Param("walletId") Long walletId);
}
