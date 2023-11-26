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
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainViewController {
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Label lblNombre;

    @javafx.fxml.FXML
    private TableView<Order> tblPedidos;
    @javafx.fxml.FXML
    private TableColumn<Order, Integer> cCodigo;
    @javafx.fxml.FXML
    private TableColumn<Order, Date> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Order, Integer> cTotal;

    @javafx.fxml.FXML
    private ComboBox<Integer> cbCodPedido;
    @javafx.fxml.FXML
    private TextField txtFecha;


    @javafx.fxml.FXML
    private TableView<Product> tblProductos;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> cCodProducto;
    @javafx.fxml.FXML
    private TableColumn<Product, String> cProducto;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> cCantidad;

    @javafx.fxml.FXML
    private ComboBox<String> cbNombre;
    @javafx.fxml.FXML
    private TextField txtPrecio;
    @javafx.fxml.FXML
    private TextField txtCantidad;


    @javafx.fxml.FXML
    private Button btnDate;
    @javafx.fxml.FXML
    private Button btnCerrar;
    @javafx.fxml.FXML
    private Button btnActProducto;
    @javafx.fxml.FXML
    private Button btnAñaProducto;
    @javafx.fxml.FXML
    private Button btnBorrarProducto;

    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();
    //Product p = Session.getCurentProduct();
    User u = Session.getCurentUser();
    Order o = Session.getCurrentOrder();
    Item i = Session.getCurrentItem();

    private final Long userId = u.getId();


    @javafx.fxml.FXML
    public void initialize() {
        lblNombre.setText(String.valueOf(Session.getCurentUser().getNombre()));
        info.setText("");

        //---------------PEDIDOS

        cCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        cFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        List<Order> pedidos = orderDAO.getAllByUserId(userId);
        tblPedidos.getItems().addAll(pedidos);

        tblPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, order, t1)->{
            Session.setCurrentOrder(t1);
            mostrarPedido(t1);
        });

        List<Integer> orderCodes = (new OrderDAO()).getOrderText(userId);
        cbCodPedido.getItems().addAll(orderCodes);


        //---------------PRODUCTOS

        List<String> nombrePedidos = productDAO.getAllNames();
        cbNombre.getItems().addAll(nombrePedidos);

        tblProductos.getSelectionModel().selectedItemProperty().addListener((observableValue, product, p1)->{
            Session.setCurentProduct(p1);
            mostrarProducto(p1);
        });



    }

    public void mostrarPedido(Order o) {
        System.out.println(o);
        if(o!=null){
            rellenarPedido(o);

        }
    }

    private void rellenarPedido(Order o) {
        txtFecha.setText(String.valueOf(o.getFecha()));
        cbCodPedido.getSelectionModel().select(o.getCodigo());


        //----------------TABLA DE PRODUCTOS

        tblProductos.getItems().clear();

        cProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cCodProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
        //cCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        cCantidad.setCellValueFactory(cellData -> {
            List<Item> itemsByUser = cellData.getValue().getItemsByUser(userId);
            int totalQuantity = itemsByUser.stream().mapToInt(Item::getCantidad).sum();
            return new SimpleIntegerProperty(totalQuantity).asObject();
        });


        List<Product> products = productDAO.getProducts(o.getId(),userId);
        tblProductos.getItems().addAll(products);
    }


    public void mostrarProducto(Product p1) {
        System.out.println(p1);
        if(p1 != null){
            cbNombre.setValue(p1.getNombre());
            txtPrecio.setText(String.valueOf(p1.getPrecio()));
            txtCantidad.setText(String.valueOf(p1.getCantidad()));
        }

    }



    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println(u.getNombre()+": Logging out");

        App.changeScene("login-view.fxml","Login");
    }

    @javafx.fxml.FXML
    public void update(ActionEvent actionEvent) {
        Product p = Session.getCurentProduct();
        if(p.getId()!=null){
            productDAO.update(p);
        }else{
            productDAO.save(p);
        }
        System.out.println("update");
    }

    @javafx.fxml.FXML
    public void add(ActionEvent actionEvent) {
        Product p = Session.getCurentProduct();
        System.out.println("add");

        fechaActual();

        if(cbNombre.getValue().length()>1) p.setNombre(cbNombre.getValue());
        if(txtPrecio.getText().length()>1) p.setPrecio(Integer.parseInt(txtPrecio.getText()));
        if(txtCantidad.getText().length()>1) p.setCantidad(Integer.parseInt(txtCantidad.getText()));

        if(p.getId()!=null){
            productDAO.update(p);
        }else{
            productDAO.save(p);
        }

    }


    @javafx.fxml.FXML
    public void delete(ActionEvent actionEvent) {
        System.out.println("delete");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar "+Session.getCurentProduct().getNombre()+" del listado?");

        var result = alert.showAndWait().get();

        if(result.getButtonData()==ButtonBar.ButtonData.OK_DONE){
            productDAO.delete(Session.getCurentProduct());
            System.out.println("Producto Borrado");
        }
    }


    @javafx.fxml.FXML
    public void fechaActual() {
        txtFecha.setText(String.valueOf(LocalDate.now()));
    }


}