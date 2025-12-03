package com.ecommerce.techlab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LineaPedido> lineasPedido = new ArrayList<>();

    @NotNull(message = "El estado del pedido es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Pedido() {

    }

    public Pedido(Usuario usuario) {
        this.usuario = usuario; // Asume que tienes un campo 'usuario' en la clase Pedido
    }

    public BigDecimal calcularTotal() {
        BigDecimal totalCalculado = BigDecimal.ZERO;
        for (LineaPedido linea : lineasPedido) {
            totalCalculado = totalCalculado.add(linea.calcularSubtotal());
        }
        this.total = totalCalculado;
        return totalCalculado;
    }

    public void agregarLineaPedido(LineaPedido lineaPedido) {
        lineasPedido.add(lineaPedido);
        lineaPedido.setPedido(this);
        calcularTotal();
    }

    public void removerLineaPedido(LineaPedido lineaPedido) {
        lineasPedido.remove(lineaPedido);
        lineaPedido.setPedido(null);
        calcularTotal();
    }

    public void confirmar() {
        if (this.estado != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden confirmar pedidos pendientes");
        }

        for (LineaPedido linea : lineasPedido) {
            Producto producto = linea.getProducto();
            if (!producto.tieneStockSuficiente(linea.getCantidad())) {
                throw new IllegalStateException(
                        "Stock insuficiente para el producto: " + producto.getNombre()
                );
            }
        }

        for (LineaPedido linea : lineasPedido) {
            linea.getProducto().disminuirStock(linea.getCantidad());
        }

        this.estado = EstadoPedido.CONFIRMADO;
        this.fechaActualizacion = LocalDateTime.now();
    }
}
