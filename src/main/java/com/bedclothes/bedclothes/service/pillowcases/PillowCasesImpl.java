package com.bedclothes.bedclothes.service.pillowcases;

import com.bedclothes.bedclothes.dto.PillowsDto;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Pillowcases;
import com.bedclothes.bedclothes.repository.PillowCasesRepository;
import com.bedclothes.bedclothes.request.CreatePillowRequest;
import com.bedclothes.bedclothes.request.UpdatePillowsRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PillowCasesImpl implements PillowCasesService {

    private final PillowCasesRepository pillowsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Pillowcases createPillowCases(CreatePillowRequest req, Clothes clothes) {
        List<Pillowcases> cases = pillowsRepository.findByNameAndSizeAndColor(req.getName(), req.getSize(), req.getColor());

        if (!cases.isEmpty() || clothes.getPillowcases() != null) {
            // If duplicates exist, increment the quantity of the first found pillow
            Pillowcases existingCase = cases.get(0);
            existingCase.setQuantity(existingCase.getQuantity() + 1);
            return pillowsRepository.save(existingCase);
        }

        Pillowcases pillowcases = new Pillowcases();
        pillowcases.setName(req.getName());
        pillowcases.setSize(req.getSize());
        pillowcases.setColor(req.getColor());
        pillowcases.setBrand(req.getBrand());
        pillowcases.setPrice(req.getPrice());
        pillowcases.setDescription(req.getDescription());
        pillowcases.setQuantity(1);

        return pillowsRepository.save(pillowcases);
    }

    @Override
    public Pillowcases updatePillowCases(UpdatePillowsRequest pillowcases, Long pillowcasesId) {
        Pillowcases psc = pillowsRepository.findById(pillowcasesId).orElseThrow(()->
                new EntityNotFoundException("Pillowcases not found"));

        if (pillowcases.getSize() != null) {
            psc.setName(pillowcases.getSize());
        }
        if (pillowcases.getDescription() != null) {
            psc.setDescription(pillowcases.getDescription());
        }
        if (pillowcases.getPrice() != null) {
            psc.setPrice(pillowcases.getPrice());
        }
        return pillowsRepository.save(psc);
    }

    @Override
    public List<Pillowcases> getAllPillowCases() {
        return pillowsRepository.findAll();
    }

    @Override
    public void deletePillowCases(Long pillowcasesId) {
        pillowsRepository.findById(pillowcasesId).ifPresent(pillowsRepository::delete);

    }
    @Override
    public PillowsDto convertPillowCasesToDto(Pillowcases pillowCases) {
        return modelMapper.map(pillowCases, PillowsDto.class);
    }
    @Override
    public List<PillowsDto> convertPillowCasesToDto(List<Pillowcases> pillowCasesList) {
        return pillowCasesList.stream()
                .map(this::convertPillowCasesToDto)
                .toList();
    }
}
