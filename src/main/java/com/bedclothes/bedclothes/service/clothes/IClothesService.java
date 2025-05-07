package com.bedclothes.bedclothes.service.clothes;

import com.bedclothes.bedclothes.dto.ClothesDto;
import com.bedclothes.bedclothes.exception.ClothesNotFoundException;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.request.CreateClothesRequest;
import com.bedclothes.bedclothes.request.UpdateClothesRequest;

public interface IClothesService {

    Clothes getClothesById(Long clothesId);

    Clothes createClothes(CreateClothesRequest req);

    Clothes updateClothes(Long clothesId, UpdateClothesRequest updReq) throws ClothesNotFoundException;

    void deleteClothes(Long clothesId) throws ClothesNotFoundException;


    ClothesDto convertToClothesDto(Clothes clothes);
}
