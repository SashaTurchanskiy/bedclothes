package com.bedclothes.bedclothes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clothes")
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clothesName;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private String description;
    private String image;

    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private Blanket blanket;
    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private Duvet duvet;
    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private Quilt quilt;
    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private Pillows pillows;
    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private Pilowcases pilowcases;
}
