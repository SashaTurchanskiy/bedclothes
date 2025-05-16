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
@Table(name = "clothes")
public class Clothes implements ImageAttachable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clothesName;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private String description;


    @ManyToOne
    @JoinColumn(name = "blanket_id")
    private Blanket blanket;

    @ManyToOne
    @JoinColumn(name = "duvet_id")
    private Duvet duvet;

    @ManyToOne
    @JoinColumn(name = "quilt_id")
    private Quilt quilt;

    @ManyToOne
    @JoinColumn(name = "pillows_id")
    private Pillows pillows;

    @ManyToOne
    @JoinColumn(name = "pilowcases_id")
    private Pillowcases pillowcases;

    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private List<Image> image = new ArrayList<>();

    @Override
    public void addImage(Image image) {
        this.image.add(image);
        image.setClothes(this);
    }
}
