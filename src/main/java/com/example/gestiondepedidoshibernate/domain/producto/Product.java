package com.example.gestiondepedidoshibernate.domain.producto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "productos")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int precio;
    @Column(name = "cantidad_disponible")
    private int cantidad_dis;

    //todo: Relacionar productos con usuarios

    public static void merge(Product origen, Product destino) {
        destino.setNombre(origen.getNombre());
        destino.setPrecio(origen.getPrecio());
        destino.setCantidad_dis(origen.getCantidad_dis());
    }


}
