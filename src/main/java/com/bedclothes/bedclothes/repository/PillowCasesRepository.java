package com.bedclothes.bedclothes.repository;

import com.bedclothes.bedclothes.model.Pillowcases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PillowCasesRepository extends JpaRepository<Pillowcases, Long> {
    List<Pillowcases> findByNameAndSizeAndColor(String brand, String size, String color);
}
