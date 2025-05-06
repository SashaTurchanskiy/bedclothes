package com.bedclothes.bedclothes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pillows")
public class Pillows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private String color;
    private String brand;
    private BigDecimal price;
    private String description;


    @OneToMany(mappedBy = "pillows")
    private List<Clothes> clothes;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
