package com.ecommerce.techlab.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Long categoriaId;
    private String categoriaNombre;
    private String imagenUrl;
    private Integer stock;
}
