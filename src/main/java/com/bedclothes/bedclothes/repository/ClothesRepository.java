package com.bedclothes.bedclothes.repository;

import com.bedclothes.bedclothes.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Optional<Clothes> findByClothesName(String clothesName);
}
