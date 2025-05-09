package com.bedclothes.bedclothes.repository;

import com.bedclothes.bedclothes.model.Pillows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PillowsRepository extends JpaRepository<Pillows, Long> {
    List<Pillows> findByNameAndSizeAndColor(String name, String size, String color);
}
