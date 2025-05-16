package com.bedclothes.bedclothes.service.pilows;

import com.bedclothes.bedclothes.dto.ImageDto;
import com.bedclothes.bedclothes.dto.PillowsDto;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Pillows;
import com.bedclothes.bedclothes.request.CreatePillowRequest;
import com.bedclothes.bedclothes.request.UpdatePillowsRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPillowsService {

    Pillows createPillow(CreatePillowRequest req, Clothes clothes);
    Pillows updatePillow(Long pillowId, UpdatePillowsRequest updReq);
    List<Pillows> getAllPillows();
    Pillows getPillowById(Long pillowId);
    void deletePillow(Long pillowId);

    PillowsDto convertPillowToDto(Pillows pillows);

    List<PillowsDto> convertPillowToDto(List<Pillows> pillowsList);

    ImageDto savedImage(MultipartFile file, Long pillowsId);
}
