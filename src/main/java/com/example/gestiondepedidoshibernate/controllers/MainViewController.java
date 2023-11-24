package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.App;
import com.example.gestiondepedidoshibernate.Session;
import com.example.gestiondepedidoshibernate.domain.item.Item;
import com.example.gestiondepedidoshibernate.domain.pedido.Order;
import com.example.gestiondepedidoshibernate.domain.pedido.OrderDAO;
import com.example.gestiondepedidoshibernate.domain.producto.Product;
import com.example.gestiondepedidoshibernate.domain.producto.ProductDAO;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import com.example.gestiondepedidoshibernate.domain.usuario.UserDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainViewController {
    @javafx.fxml.FXML
    private TableView<Product> tblPedidos;

    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Label lblNombre;

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
    private TableColumn<Order, Integer> cCodigo;
    @javafx.fxml.FXML
    private TableColumn<Order, Date> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Order, Integer> cTotal;

    @javafx.fxml.FXML
    private TableColumn<Product, Integer> cCodProducto;
    @javafx.fxml.FXML
    private TableColumn<Product, String> cProducto;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> cCantidad;

    @javafx.fxml.FXML
    private Button btnCerrar;
    @javafx.fxml.FXML
    private Button btnActPedido;
    @javafx.fxml.FXML
    private Button btnAñaPedido;
    @javafx.fxml.FXML
    private Button btnBorrarPedido;
    @javafx.fxml.FXML
    private Button btnActProducto;
    @javafx.fxml.FXML
    private Button btnAñaProducto;
    @javafx.fxml.FXML
    private Button btnBorrarProducto;

    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();
    Product p = Session.getCurentProduct();
    User u = Session.getCurentUser();
    Order o = Session.getCurrentOrder();
    Item i = Session.getCurrentItem();


    @javafx.fxml.FXML
    public void initialize() {
        lblNombre.setText(String.valueOf(Session.getCurentUser().getNombre()));
        info.setText("");


        //cCodigo.setCellValueFactory((fila)-> new SimpleStringProperty(fila.getValue().getCodigo()+""));
        cCodigo.setCellValueFactory(fila -> {
            Integer codigo = fila.getValue().getCodigo();
            return new SimpleIntegerProperty(codigo).asObject();
        });

        cFecha.setCellValueFactory((fila)->{
            Date fecha = fila.getValue().getFecha();
            return (ObservableValue<Date>) new SimpleDateFormat(fecha+"");
        });

        /*cTotal.setCellValueFactory(fila->

        );*/

//        Session.setCurentUser((new UserDAO()).get(Session.getCurentUser().getId()));
//        tblPedidos.getItems().addAll((Product) Session.getCurentUser().getOrders());

        Order pedido = orderDAO.get(u.getId());

        txtFecha.setText(String.valueOf(pedido.getFecha()));
        txtTotal.setText(String.valueOf(pedido.getTotal()));

        //cbCodPedido.getItems().addAll((new OrderDAO()).getAll());
        cbCodPedido.setValue(pedido);

    }

    @javafx.fxml.FXML
    public void mostrarPedido(Event event) {

    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println(u.getNombre()+": Logging out");

        App.changeScene("login-view.fxml","Login");
    }

    @javafx.fxml.FXML
    public void update(ActionEvent actionEvent) {
        /*if(p.getId()!=null){
            productDAO.update(p);
        }else{
            productDAO.save(p);
        }*/
        System.out.println("update");
    }

    @javafx.fxml.FXML
    public void add(ActionEvent actionEvent) {
        System.out.println("add");
    }

    @javafx.fxml.FXML
    public void delete(ActionEvent actionEvent) {
        System.out.println("delete");
    }


}