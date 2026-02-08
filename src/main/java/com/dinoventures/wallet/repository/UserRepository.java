package com.dinoventures.wallet.repository;

import com.dinoventures.wallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
