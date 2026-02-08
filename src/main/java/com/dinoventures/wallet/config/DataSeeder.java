package com.dinoventures.wallet.config;

import com.dinoventures.wallet.entity.AssetType;
import com.dinoventures.wallet.entity.User;
import com.dinoventures.wallet.entity.Wallet;
import com.dinoventures.wallet.repository.AssetRepository;
import com.dinoventures.wallet.repository.UserRepository;
import com.dinoventures.wallet.repository.WalletRepository;
import com.dinoventures.wallet.service.WalletService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
            AssetRepository assetRepository,
             WalletRepository walletRepository,
         WalletService walletService) {
        return args -> {
            
            User john = userRepository.save(new User(null, "John Doe"));
             User jane = userRepository.save(new User(null, "Jane Smith"));

         AssetType usd = assetRepository.save(new AssetType(null, "USD"));
            AssetType eur = assetRepository.save(new AssetType(null, "EUR"));

         Wallet johnUsd = walletRepository.save(new Wallet(null, john, usd));
            Wallet janeUsd = walletRepository.save(new Wallet(null, jane, usd));
             Wallet johnEur = walletRepository.save(new Wallet(null, john, eur));
 
            System.out.println("Seeded Users: " + john.getId() + ", " + jane.getId());
             System.out.println("Seeded Wallets: " + johnUsd.getId() + ", " + janeUsd.getId());

                walletService.topup(john.getId(), "USD", 10000L, UUID.randomUUID().toString()); // $100.00
            walletService.topup(jane.getId(), "USD", 5000L, UUID.randomUUID().toString()); // $50.00

            System.out.println("Seeded Initial Transactions");
        };
    }
}
