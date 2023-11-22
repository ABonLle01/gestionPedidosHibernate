package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.App;
import com.example.gestiondepedidoshibernate.Session;
import com.example.gestiondepedidoshibernate.domain.pedido.Order;
import com.example.gestiondepedidoshibernate.domain.producto.Product;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import java.io.IOException;

public class MainViewController {
    @javafx.fxml.FXML
    private TableView<Product> tblPedidos;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> codigo;
    @javafx.fxml.FXML
    private TableColumn<Product, String> fecha;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> total;

    @javafx.fxml.FXML
    private TextField txtFecha;
    @javafx.fxml.FXML
    private TextField txtTotal;
    @javafx.fxml.FXML
    private ComboBox<Order> cbCodPedido;
    @javafx.fxml.FXML
    private TableView<Product> tblProductos;
    @javafx.fxml.FXML
    private TextField txtCantidad;
    @javafx.fxml.FXML
    private ComboBox<Product> cbNombre;
    @javafx.fxml.FXML
    private ComboBox<Product> cbCodigoProducto;

    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Label lblNombre;
    @javafx.fxml.FXML
    private Button btnCerrar;




    @javafx.fxml.FXML
    public void initialize() {
        lblNombre.setText(String.valueOf(Session.getCurentUser().getNombre()));
        info.setText("");





    }

    @javafx.fxml.FXML
    public void mostrarPedido(Event event) {

    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println(Session.getCurentUser().getNombre()+": Logging out");

        App.changeScene("login-view.fxml","Login");
    }
}