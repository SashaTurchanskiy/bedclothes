package com.bedclothes.bedclothes.service.pilows;

import com.bedclothes.bedclothes.dto.PillowsDto;
import com.bedclothes.bedclothes.exception.PillowsNotFoundException;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Pillows;
import com.bedclothes.bedclothes.repository.PillowsRepository;
import com.bedclothes.bedclothes.request.CreatePillowRequest;
import com.bedclothes.bedclothes.request.UpdatePillowsRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PillowService implements IPillowsService {

    private final PillowsRepository pillowsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Pillows createPillow(CreatePillowRequest req, Clothes clothes) {
        // Check if a pillow with the same attributes already exists
        List<Pillows> existingPillows = pillowsRepository.findByNameAndSizeAndColor(req.getName(), req.getSize(), req.getColor());

        if (!existingPillows.isEmpty() || clothes.getPillows() != null) {
            // If duplicates exist, increment the quantity of the first found pillow
            Pillows existingPillow = existingPillows.get(0);
            existingPillow.setQuantity(existingPillow.getQuantity() + 1);
            return pillowsRepository.save(existingPillow); // Return the updated pillow
        }
        // Proceed to create a new pillow if it doesn't exist
            Pillows pillows = new Pillows();
            pillows.setName(req.getName());
            pillows.setSize(req.getSize());
            pillows.setColor(req.getColor());
            pillows.setBrand(req.getBrand());
            pillows.setPrice(req.getPrice());
            pillows.setDescription(req.getDescription());
            pillows.setQuantity(1); // Set initial quantity to 1
            return pillowsRepository.save(pillows);



        // Return the created pillows object
    }

    @Override
    public Pillows updatePillow(Long pillowId, UpdatePillowsRequest updReq) {
        Pillows existingPillow = pillowsRepository.findById(pillowId).orElseThrow(() ->
                new PillowsNotFoundException("Pillow with id " + pillowId + " not found"));

        if (updReq.getSize() != null){
            existingPillow.setSize(updReq.getSize());
        }

        if (updReq.getDescription() != null){
            existingPillow.setDescription(updReq.getDescription());
        }

        if (updReq.getPrice() != null){
            existingPillow.setPrice(updReq.getPrice());
        }
       return pillowsRepository.save(existingPillow);


    }

    @Override
    public List<Pillows> getAllPillows() {
        return pillowsRepository.findAll();
    }

    @Override
    public Pillows getPillowById(Long pillowId) {
        return pillowsRepository.findById(pillowId).orElseThrow(()->
                new PillowsNotFoundException("Pillow not found"));
    }

    @Override
    public void deletePillow(Long pillowId) {
        pillowsRepository.findById(pillowId).ifPresent(pillowsRepository::delete);

    }
    @Override
    public PillowsDto convertPillowToDto(Pillows pillows) {
        return modelMapper.map(pillows, PillowsDto.class);
    }
    @Override
    public List<PillowsDto> convertPillowToDto(List<Pillows> pillowsList) {
        return pillowsList.stream()
                .map(this::convertPillowToDto)
                .toList();
    }

}



