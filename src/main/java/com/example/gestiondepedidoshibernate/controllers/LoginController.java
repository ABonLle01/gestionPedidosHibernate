package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.App;
import com.example.gestiondepedidoshibernate.Session;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import com.example.gestiondepedidoshibernate.domain.usuario.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnAcceder;

    @FXML
    public void login(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String pass = txtPassword.getText();

        if(email.length()<4 || pass.length()<4){
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        }else{
            User u = (new UserDAO()).validateUser(email,pass);

            if(u==null) {
                info.setText("No encontrado");
                info.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }else{
                info.setText("Usuario: "+email+"("+pass+") correcto");
                info.setStyle("-fx-background-color: green; -fx-text-fill: white;");

                Session.setCurentUser(u);

                try {
                    App.changeScene("main-view.fxml","Login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }


    }

    @Deprecated
    public void cancel(ActionEvent actionEvent) {
        txtPassword.setText("");
        txtEmail.setText("");
        info.setText("");
        info.setStyle("-fx-background-color: transparent");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //HibernateUtil.getSessionFactory();
    }

}