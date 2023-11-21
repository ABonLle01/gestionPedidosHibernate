module com.example.gestiondepedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.example.gestiondepedidoshibernate to javafx.fxml;


    exports com.example.gestiondepedidoshibernate;
    exports com.example.gestiondepedidoshibernate.controllers;
    opens com.example.gestiondepedidoshibernate.controllers to javafx.fxml;
    exports com.example.gestiondepedidoshibernate.domain;
    opens com.example.gestiondepedidoshibernate.domain to javafx.fxml;
}