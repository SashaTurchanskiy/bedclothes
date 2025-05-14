package com.bedclothes.bedclothes.service.clothes;

import com.bedclothes.bedclothes.dto.ClothesDto;
import com.bedclothes.bedclothes.exception.ClothesNotFoundException;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.repository.ClothesRepository;
import com.bedclothes.bedclothes.request.CreateClothesRequest;
import com.bedclothes.bedclothes.request.UpdateClothesRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothesService implements IClothesService {

    private final ClothesRepository clothesRepository;
    private final ModelMapper modelMapper;

    @Override
    public Clothes getClothesById(Long clothesId) {
        return clothesRepository.findById(clothesId).orElseThrow(()->
               new EntityNotFoundException("Clothes not found"));
    }

    @Override
    public Clothes createClothes(CreateClothesRequest req) {

        if (clothesRepository.findByClothesName(req.getClothesName()).isPresent()) {
            throw new IllegalArgumentException("Clothes with name " + req.getClothesName() + " already exists");
        }
        Clothes clts = new Clothes();
        clts.setClothesName(req.getClothesName());
        clts.setBrand(req.getBrand());
        clts.setColor(req.getColor());
        clts.setSize(req.getSize());
        clts.setPrice(req.getPrice());
        clts.setDescription(req.getDescription());
        //clts.setImage(req.getImage());

        return clothesRepository.save(clts);

    }

    @Override
    public Clothes updateClothes(Long clothesId, UpdateClothesRequest updReq) throws ClothesNotFoundException {
        Clothes existingClothes = clothesRepository.findById(clothesId).orElseThrow(() ->
                new ClothesNotFoundException("Clothes with id " + clothesId + " not found"));

        if (updReq.getDescription() != null){
            existingClothes.setDescription(updReq.getDescription());
        }
        if (updReq.getPrice() != null){
            existingClothes.setPrice(updReq.getPrice());
        }
        return clothesRepository.save(existingClothes);
    }

    @Override
    public void deleteClothes(Long clothesId) throws ClothesNotFoundException {
        Clothes clothes = clothesRepository.findById(clothesId).orElseThrow(() ->
                    new ClothesNotFoundException("Clothes with id " + clothesId + " not found"));
            clothesRepository.delete(clothes);



    }

    @Override
    public List<Clothes> getAllClothes() {
        return clothesRepository.findAll();
    }

    @Override
    public ClothesDto convertToClothesDto(Clothes clothes) {
        return modelMapper.map(clothes, ClothesDto.class);
    }
    @Override
    public List<ClothesDto> convertToClothesToDto(List<Clothes> clothesList) {
        return clothesList.stream()
                .map(this::convertToClothesDto) // Corrected line
                .toList();
    }

}
