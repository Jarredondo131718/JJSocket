package com.visualsmic.vshuellassocket;


import Services.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnLogout;

    @FXML
    private PasswordField txtPassWord;

    @FXML
    private TextField txtUsuario;
    @FXML
    private Label lblError;
    @FXML
    void btnLoginOnAction(ActionEvent event) {
        Login login = new Login();
        if(txtUsuario.getLength()<2 || txtPassWord.getLength()<1)  {
            lblError.setText("Debe Ingresar Información");
            return;
        }
//         Login L = new Login("JA", "C");
        try {
            if(!login.LoginIN(txtUsuario.getText(), txtPassWord.getText())){
                MostrarErrorLogin();
            }

        }catch (Exception e){
            System.err.println("error ****************"+e.getMessage());
        };


        //Login L = new Login(txtUsuario.getText(), txtPassWord.getText());

//        boolean inOk=txtUsuario.toString().length()>0

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        App.setRoot("frmEnrollment");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setText("");
        btnLogin.setDisable(true);
        Login login = new Login();
    }
    @FXML
    void OnKeyTypedUser(KeyEvent event) {

     if(txtUsuario.getLength()>=2 && txtPassWord.getLength()>=1){
         btnLogin.setDisable(false);
         lblError.setText("");
     }
    }

    @FXML
    void OnKeyTypedPassWord(KeyEvent event) {

        if(txtUsuario.getLength()>=2 && txtPassWord.getLength()>=1){
            btnLogin.setDisable(false);
            lblError.setText("");
        }
    }
    int mintentos=0;
    private boolean MostrarErrorLogin() {
        mintentos++;

        if(mintentos>3) System.exit(0);
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Usuario No Existe, Ingreses Nuevamente sus Datos, Intentos: "+mintentos);
        a.show();
        return  false;
    }
}
