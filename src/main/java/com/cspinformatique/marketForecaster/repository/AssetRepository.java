package com.cspinformatique.marketForecaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.marketForecaster.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

}
