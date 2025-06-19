package com.bedclothes.bedclothes.controller;

import com.bedclothes.bedclothes.dto.ClothesDto;
import com.bedclothes.bedclothes.exception.ClothesNotFoundException;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.request.CreateClothesRequest;
import com.bedclothes.bedclothes.request.UpdateClothesRequest;
import com.bedclothes.bedclothes.service.clothes.IClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothes")
@RequiredArgsConstructor
public class ClothesController {

    private final IClothesService clothesService;


    @PostMapping("/create")
    public ResponseEntity<ClothesDto> createClothes(@RequestBody CreateClothesRequest req) {
        Clothes clothes = clothesService.createClothes(req);
        ClothesDto clothesDto = clothesService.convertToClothesDto(clothes);
        return ResponseEntity.ok(clothesDto);
    }
    @GetMapping("/{clothesId}")
    public ResponseEntity<ClothesDto> getClothesById(@PathVariable Long clothesId) {
        Clothes clothes = clothesService.getClothesById(clothesId);
        ClothesDto clothesDto = clothesService.convertToClothesDto(clothes);
        return ResponseEntity.ok(clothesDto);
    }
    @PutMapping("/update/{clothesId}")
    public ResponseEntity<ClothesDto> updateClothes(@PathVariable Long clothesId, @RequestBody UpdateClothesRequest updReq) throws ClothesNotFoundException {
        Clothes clothes = clothesService.updateClothes(clothesId, updReq);
        ClothesDto clothesDto = clothesService.convertToClothesDto(clothes);
        return ResponseEntity.ok(clothesDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ClothesDto>> getAllClothes() {
        List<Clothes> clothes = clothesService.getAllClothes();
        List<ClothesDto> clothesDto = clothesService.convertToClothesToDto(clothes);
        return ResponseEntity.ok(clothesDto);
    }

    @DeleteMapping("/delete/{clothesId}")
    public ResponseEntity<String> deleteClothes(@PathVariable Long clothesId) throws ClothesNotFoundException {
        try {
            clothesService.deleteClothes(clothesId);
            return ResponseEntity.ok("Clothes deleted successfully");
        } catch (ClothesNotFoundException e) {
            return ResponseEntity.ok("Clothes" + clothesId + "not found");
        }
    }
}
