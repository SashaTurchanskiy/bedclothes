package com.bedclothes.bedclothes.model;

import com.bedclothes.bedclothes.utils.ImageAttachable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pillows")
public class Pillows implements ImageAttachable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private String color;
    private String brand;
    private BigDecimal price;
    private String description;
    private int quantity;

    // Поля для зображення
    private Long imageId; // ID зображення
    private String fileName; // Ім'я файлу
    private String downloadUrl; // URL для завантаження


    @OneToMany(mappedBy = "pillows")
    private List<Clothes> clothes;

    @OneToMany(mappedBy = "pillows", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> image = new ArrayList<>();

    @Override
    public void addImage(Image image) {
        this.image.add(image);
        image.setPillows(this);
    }
}
