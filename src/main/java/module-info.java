module com.example.gestiondepedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.example.gestiondepedidoshibernate;
    opens com.example.gestiondepedidoshibernate.controllers;
    opens com.example.gestiondepedidoshibernate.domain;

    opens com.example.gestiondepedidoshibernate.domain.usuario;
    opens com.example.gestiondepedidoshibernate.domain.pedido;
    opens com.example.gestiondepedidoshibernate.domain.producto;
    opens com.example.gestiondepedidoshibernate.domain.item;

    exports com.example.gestiondepedidoshibernate;
    exports com.example.gestiondepedidoshibernate.controllers;
    exports com.example.gestiondepedidoshibernate.domain;
}