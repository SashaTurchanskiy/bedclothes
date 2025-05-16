package com.bedclothes.bedclothes.controller;

import com.bedclothes.bedclothes.dto.ImageDto;
import com.bedclothes.bedclothes.dto.PillowsDto;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Image;
import com.bedclothes.bedclothes.model.Pillows;
import com.bedclothes.bedclothes.request.CreatePillowRequest;
import com.bedclothes.bedclothes.request.UpdatePillowsRequest;
import com.bedclothes.bedclothes.service.image.ImageService;
import com.bedclothes.bedclothes.service.pilows.IPillowsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/pillows")
@RequiredArgsConstructor
public class PillowsController {

    private final IPillowsService pillowsService;
    private final ImageService imageService;

    @PostMapping("/image/upload")
    public ResponseEntity<ImageDto> addImage(@RequestParam("file") MultipartFile file,
                                             @RequestParam("pillowsId") Long pillowsId) {
        ImageDto imageDto = pillowsService.savedImage(file, pillowsId);
        return ResponseEntity.ok(imageDto);
    }
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        var resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName()
                        + "\"").body(resource);
    }

    @PostMapping("/create")
    public ResponseEntity<PillowsDto> createPillow(@RequestBody CreatePillowRequest req, Clothes clothes){
        Pillows pillows = pillowsService.createPillow(req, clothes);
       //PillowsDto convert = pillowsService.convertPillowToDto(pillows);
        return ResponseEntity.ok(pillowsService.convertPillowToDto(pillows));
    }

    @PutMapping("/update/{pillowsId}")
    public ResponseEntity<PillowsDto> updatePillow(@PathVariable Long pillowsId,@RequestBody UpdatePillowsRequest req){
        Pillows pillows = pillowsService.updatePillow(pillowsId, req);
        PillowsDto pdto = pillowsService.convertPillowToDto(pillows);
        return ResponseEntity.ok(pdto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PillowsDto>> getAllPillows(){
        List<Pillows> pillows = pillowsService.getAllPillows();
        List<PillowsDto> dtos = pillowsService.convertPillowToDto(pillows);
        return ResponseEntity.ok(dtos);
    }
    @DeleteMapping("/delete/{pillowsId}")
    public ResponseEntity<String> deletePillow(@PathVariable Long pillowsId){
        try {
            pillowsService.deletePillow(pillowsId);
            return ResponseEntity.ok("Pillow deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.ok("Pillow" + pillowsId + "not found");
        }
    }
}
