package com.bedclothes.bedclothes.service.image;

import com.bedclothes.bedclothes.dto.ImageDto;
import com.bedclothes.bedclothes.exception.ImageNotFoundException;
import com.bedclothes.bedclothes.exception.ImageProcessingException;
import com.bedclothes.bedclothes.model.Image;
import com.bedclothes.bedclothes.repository.ImageRepository;
import com.bedclothes.bedclothes.service.clothes.ClothesService;
import com.bedclothes.bedclothes.utils.ImageAttachable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ClothesService clothesService;

    @Override
    public Image getImageById(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(() ->
                new ImageNotFoundException("Image not found " + imageId));
    }

    @Override
    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() ->
                new ImageNotFoundException("Image not found " + imageId));
        imageRepository.delete(image);

    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends ImageAttachable> ImageDto saveImage(MultipartFile file, T attachableEntity, Consumer<Image> entitySetter) {
        validateFile(file);
        try {
            Image image = createImageFromFile(file);

            // Зв'язуємо з сутністю
            entitySetter.accept(image);
            attachableEntity.addImage(image);

            Image savedImage = imageRepository.save(image);
            savedImage.setDownloadUrl("/api/images/image/download/" + savedImage.getId());
            imageRepository.save(savedImage);

            return convertToDto(savedImage);

        } catch (SQLException | IOException e) {
            throw new ImageProcessingException("Failed to save image", e);
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Invalid file: must be a non-empty image file.");
        }
    }

    private Image createImageFromFile(MultipartFile file) throws SQLException, IOException {
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setImage(new SerialBlob(file.getBytes()));
        return image;
    }

    private ImageDto convertToDto(Image savedImage) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(savedImage.getId());
        imageDto.setFileName(savedImage.getFileName());
        imageDto.setDownloadUrl(savedImage.getDownloadUrl());
        return imageDto;
    }
}