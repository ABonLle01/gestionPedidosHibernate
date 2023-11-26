package com.example.gestiondepedidoshibernate.domain.producto;

import com.example.gestiondepedidoshibernate.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>(0);


    @Override
    public String toString() {
        return "Product {" +
                "Id = " + id +
                ", Nombre = " + nombre +
                ", Precio = " + precio +
                ", Cantidad = " + cantidad +
                '}';
    }

    public List<Item> getItemsByUser(Long userId) {
        return items.stream()
                .filter(item -> item.getCodigo().getUsuario().getId().equals(userId))
                .collect(Collectors.toList());
    }


    public static void merge(Product origen, Product destino) {
        destino.setNombre(origen.getNombre());
        destino.setPrecio(origen.getPrecio());
        destino.setCantidad(origen.getCantidad());
    }


}
