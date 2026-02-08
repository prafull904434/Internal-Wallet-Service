package entity;

import jakarta.persistence.Entity;

@Entity
@Table(name = "asset_types")
public class AssetType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;
}

