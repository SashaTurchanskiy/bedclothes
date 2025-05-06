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

    @OneToOne(mappedBy = "clothes", cascade = CascadeType.ALL)
    private Image image;
}
