package com.example.gestiondepedidoshibernate.domain.item;

import com.example.gestiondepedidoshibernate.domain.pedido.Order;
import com.example.gestiondepedidoshibernate.domain.producto.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "codigo",referencedColumnName ="codigo_pedido")
    private Order codigo;

    @ManyToOne
    @JoinColumn(name = "producto")
    private Product producto;





}
