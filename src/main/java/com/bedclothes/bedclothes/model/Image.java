package com.bedclothes.bedclothes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private Blob image;

    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    @ManyToOne
    @JoinColumn(name = "pillows_id")
    private Pillows pillows;
    @OneToOne
    @JoinColumn(name = "pillowcases_id")
    private Pillowcases pillowcases;
    @OneToOne
    @JoinColumn(name = "blanket_id")
    private Blanket blanket;
    @OneToOne
    @JoinColumn(name = "duvet_id")
    private Duvet duvet;
    @OneToOne
    @JoinColumn(name = "quilt_id")
    private Quilt quilt;
}
