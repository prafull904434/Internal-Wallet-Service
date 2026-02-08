package Repository;


import com.dinoventures.wallet.entity.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<AssetType, Long> {

    Optional<AssetType> findByCode(String code);
}

