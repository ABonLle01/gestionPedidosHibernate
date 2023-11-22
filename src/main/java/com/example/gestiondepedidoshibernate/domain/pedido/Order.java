package com.example.gestiondepedidoshibernate.domain.pedido;

import com.example.gestiondepedidoshibernate.domain.item.Item;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;
    @Column(name = "codigo_pedido")
    private Integer codigo;
    private String fecha;
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @OneToMany(mappedBy = "codigo", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>(0);

}
