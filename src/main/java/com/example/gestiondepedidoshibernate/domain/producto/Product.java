package com.example.gestiondepedidoshibernate.domain.producto;

import com.example.gestiondepedidoshibernate.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private int cantidad;

    /*
        @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
        private List<Item> items = new ArrayList<>(0);
    */




    public static void merge(Product origen, Product destino) {
        destino.setNombre(origen.getNombre());
        destino.setPrecio(origen.getPrecio());
        destino.setCantidad(origen.getCantidad());
    }


}
