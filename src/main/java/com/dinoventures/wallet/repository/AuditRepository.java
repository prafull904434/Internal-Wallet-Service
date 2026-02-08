package com.dinoventures.wallet.repository;

import com.dinoventures.wallet.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}
