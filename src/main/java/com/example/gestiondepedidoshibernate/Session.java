package com.example.gestiondepedidoshibernate;

import com.example.gestiondepedidoshibernate.domain.item.Item;
import com.example.gestiondepedidoshibernate.domain.pedido.Order;
import com.example.gestiondepedidoshibernate.domain.producto.Product;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import lombok.*;


public class Session {
    @Getter
    @Setter
    private static User curentUser;

    @Getter
    @Setter
    private static Product curentProduct;

    @Getter
    @Setter
    private static Order currentOrder;

    @Getter
    @Setter
    private static Item currentItem;


}
