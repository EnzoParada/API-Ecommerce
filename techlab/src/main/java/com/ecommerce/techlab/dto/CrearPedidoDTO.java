package com.ecommerce.techlab.dto;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearPedidoDTO {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "Las líneas de pedido son obligatorias")
    private List<LineaPedidoDTO> lineasPedido;

    @Data
    public static class LineaPedidoDTO {
        @NotNull(message = "El ID del producto es obligatorio")
        private Long productoId;

        @NotNull(message = "La cantidad es obligatoria")
        private Integer cantidad;
    }
}
