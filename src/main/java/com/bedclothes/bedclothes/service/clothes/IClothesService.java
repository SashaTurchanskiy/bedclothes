package com.bedclothes.bedclothes.service.clothes;

import com.bedclothes.bedclothes.dto.ClothesDto;
import com.bedclothes.bedclothes.exception.ClothesNotFoundException;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.request.CreateClothesRequest;
import com.bedclothes.bedclothes.request.UpdateClothesRequest;

import java.util.List;

public interface IClothesService {

    Clothes getClothesById(Long clothesId);

    Clothes createClothes(CreateClothesRequest req);

    Clothes updateClothes(Long clothesId, UpdateClothesRequest updReq) throws ClothesNotFoundException;

    void deleteClothes(Long clothesId) throws ClothesNotFoundException;

    List<Clothes> getAllClothes();

    ClothesDto convertToClothesDto(Clothes clothes);

    List<ClothesDto> convertToClothesToDto(List<Clothes> clothesList);
}
