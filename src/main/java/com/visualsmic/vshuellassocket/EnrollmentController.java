package com.visualsmic.vshuellassocket;

import FIngerUtils.CapturarHuella;
import javafx.geometry.Insets;
import Models.Client;
import Models.User;
import Models.Hand;
import Controllers.HandConvert;
import Services.FileManagement;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture._impl.DPFPCaptureFactoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.digitalpersona.onetouch.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EnrollmentController implements Initializable {
    Client client;
    DPFPCapture capture = new DPFPCaptureFactoryImpl().createCapture();

    CapturarHuella huella = new CapturarHuella();

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Hand> cboDedos;

    @FXML
    private ImageView containerImageDedo;

    @FXML
    private ImageView containerImageMano;

    @FXML
    private ToggleGroup gruManos;

    @FXML
    private ToggleGroup gruManos1;

    @FXML
    private ImageView imgHuella;

    @FXML
    private Label lblNameUser;

    @FXML
    private RadioButton rbtDerecha;

    @FXML
    private RadioButton rbtIzquirda;

    @FXML
    private TextField txtDocumentClient;

    @FXML
    private TextField txtNameClient;

    public EnrollmentController() throws AWTException {
    }
    @FXML
    private    Label lblMessage ;

    @FXML
    private AnchorPane containerMessage;
    @FXML
    void btnLoginOnAction(ActionEvent event)  throws IOException{
        App.setRoot("frmLogin");
    }
    @FXML
    void btnIniciarEvent(ActionEvent event) throws IOException, AWTException {
        FileManagement FM = new FileManagement();
        System.out.println("btnIniciarEvent");
        client = FM.ReadFileClient();

        huella.setClient(client);
        txtDocumentClient.setText(client.getNitClient());
        txtNameClient.setText(client.getNameClient());
        huella.Iniciar();
        

    }

    @FXML
    void btnCancelarEvent(ActionEvent event) {

    }

    @FXML
    void btnManoDerechaEvent(ActionEvent event) {
        rbtDerecha.setSelected(true);
        rbtIzquirda.setSelected(false);
        client.setNameHand("ManoDerecha");
        displayImageSelected( "ManoDerecha.png","Mano");
    }

    @FXML
    void btnManoIzquierdaEvent(ActionEvent event) {
        rbtDerecha.setSelected(false);
        rbtIzquirda.setSelected(true);
        client.setNameHand("ManoIzquierda");
        displayImageSelected( "ManoIzquierda.png","Mano");
    }
    static Label errorLabel = new Label();
    @FXML
    void cboDedosEvent(ActionEvent event) {
       displayImageSelected(cboDedos.getValue().toString() + ".png","dedos");
    }

    private void displayImageSelected(String pDedoUrl,String pcontainer) {
        try {
            Image image = new Image(getClass().getResourceAsStream(pDedoUrl));
            if(pcontainer.equals("Mano")){
                containerImageMano.setImage(image);
            }else{
                containerImageDedo.setImage(image);
            }
        } catch (Exception e) {
            MostrarAlertaBorrar("Por Cath error ", e.getMessage() + " Dedo +mDedoUrl");
        }        
    }

    public static void MostrarAlertaBorrar(String mssgUsuario, String MessajeErc) {
        // esto se puede borrar
        Alert a = new Alert(AlertType.NONE);
        a.setContentText(MessajeErc);
        a.setHeaderText(mssgUsuario);
        a.setAlertType(AlertType.ERROR);
        a.show();
    }
    public static void displayMessage(String Message) {
//este es el error , si quito el static me da error al invocar

          //  lblMessage.setText(Message);



        errorLabel.setText(Message);
        System.out.println("displayMessage Saliendo "+Message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FileManagement FM = new FileManagement();
        User user = FM.ReadFileConfig();

        rbtIzquirda.setUserData("ManoIzquierda");
        rbtDerecha.setSelected(true);

        cboDedos.getItems().addAll(new Hand().PoblarHans());
        cboDedos.setConverter(new HandConvert());
        System.out.println("com.visualsmic.vshuellassocket.EnrollmentController.initialize() user " + user);
        System.out.println("com.visualsmic.vshuellassocket.EnrollmentController.initialize() client" + client);
        lblNameUser.setText(user.getNombreUsuario());
        txtDocumentClient.setText("");
        txtNameClient.setText("");

        huella.start();

    }

}