package com.bedclothes.bedclothes.service.image;

import com.bedclothes.bedclothes.dto.ImageDto;
import com.bedclothes.bedclothes.exception.ImageNotFoundException;
import com.bedclothes.bedclothes.model.Image;
import com.bedclothes.bedclothes.utils.ImageAttachable;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Consumer;

public interface ImageService {

    Image getImageById(Long imageId) throws ImageNotFoundException;
    void deleteImage(Long imageId);
    void updateImage(MultipartFile file, Long imageId);

    <T extends ImageAttachable> ImageDto saveImage(MultipartFile file, T attachableEntity, Consumer<Image> entitySetter);
}
