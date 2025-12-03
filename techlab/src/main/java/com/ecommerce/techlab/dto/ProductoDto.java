package com.ecommerce.techlab.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Long categoriaId;
    private String categoriaNombre;
    private String imagenUrl;
    private Integer stock;

    public ProductoDto(Long id, String nombre, String descripcion, BigDecimal precio, Long categoriaId, String categoriaNombre, String imagenUrl, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.imagenUrl = imagenUrl;
        this.stock = stock;
    }
}
