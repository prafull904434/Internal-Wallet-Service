package com.dinoventures.wallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "asset_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;
}
