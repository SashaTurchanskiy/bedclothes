package com.bedclothes.bedclothes.controller;

import com.bedclothes.bedclothes.dto.ImageDto;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Image;
import com.bedclothes.bedclothes.service.clothes.ClothesService;
import com.bedclothes.bedclothes.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final ClothesService clothesService;

    @PostMapping("/image/upload")
    public ResponseEntity<ImageDto> uploadImage(@RequestParam("file") MultipartFile file,
                                                @RequestParam("clothesId") Long clothesId) {
        var clothes = clothesService.getClothesById(clothesId);
        ImageDto imageDto = imageService.saveImage(file, clothes, clothes::addImage);
        return ResponseEntity.ok(imageDto);
    }
    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<String> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {
        imageService.updateImage(file, imageId);
        return ResponseEntity.ok("Image updated successfully");
    }
    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<String> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.ok("Image deleted successfully");
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
}
