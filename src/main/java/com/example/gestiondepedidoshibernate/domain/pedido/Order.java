package com.example.gestiondepedidoshibernate.domain.pedido;

import com.example.gestiondepedidoshibernate.domain.item.Item;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_pedido")
    private Integer codigo;
    private LocalDate fecha;
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;


    @OneToMany(mappedBy = "codigo", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>(0);


    @Override
    public String toString(){
        return "Order {" +
                "Id = " + id +
                ", Codigo = " + codigo +
                ", Fecha = " + fecha +
                ", Total = " + total +
                ", Usuario = "+ usuario.getNombre() +
                "}";
    }


}
