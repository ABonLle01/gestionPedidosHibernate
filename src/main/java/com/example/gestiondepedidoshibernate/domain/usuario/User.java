package com.example.gestiondepedidoshibernate.domain.usuario;

import com.example.gestiondepedidoshibernate.domain.pedido.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;


    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>(0);


    @Transient
    private Long ordersQuantity;

    public Long getOrdersQuantity() {
        ordersQuantity = (long) orders.size();
        return ordersQuantity;
    }

}