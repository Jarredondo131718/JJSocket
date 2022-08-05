package com.visualsmic.vshuellassocket;

import com.visualsmic.vshuellassocket.FIngerUtils.CapturarHuella;
import com.visualsmic.vshuellassocket.Helper.ParameterUtils;
import com.visualsmic.vshuellassocket.Services.VSConsumeRest;
import javafx.application.Platform;
import com.visualsmic.vshuellassocket.Models.Client;
import com.visualsmic.vshuellassocket.Models.User;
import com.visualsmic.vshuellassocket.Models.Hand;
import com.visualsmic.vshuellassocket.Controllers.HandConvert;
import com.visualsmic.vshuellassocket.Services.FileManagement;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture._impl.DPFPCaptureFactoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

public class EnrollmentController implements Initializable {
    Client client;
    DPFPCapture capture = new DPFPCaptureFactoryImpl().createCapture();

    CapturarHuella huella = new CapturarHuella();

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnGuardar;

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
    private  Label lblMessageProcesos ;

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
        btnIniciar.setDisable(true);
        lblMessageProcesos.setText("");
        lblMessageProcesos.setFocusTraversable(true);
        

    }
    @FXML
    void btnGuardarEvent(ActionEvent event) {
        displayMessage("Procesando Petición, Espere Un Momento");
        btnGuardar.setDisable(true);
        VSConsumeRest HR = new VSConsumeRest();

        //System.out.println("procesarHuellaDB getImageHuella "+client.getImageHuella());
        //System.out.println("procesarHuellaDB getHuella "+client.getHuella());
        //System.out.println("aqui guardo"+client);
        ParameterUtils PU = new ParameterUtils();
        JSONObject RS = HR.ReturnServices( PU.ParameterDBEnrroll());
        System.out.println("btnGuardarEvent "+RS);

        if(RS == null){
            displayMessage("Hubo Un Problema Al Guardar La Huella");
        }else{
            displayMessage("Operación Exitosa");
            ClearMessage(10000);

        }
    }
    public  void ClearMessage(int TimeSegundos ){
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
              displayMessage("");
            }
        };

        try {
            timer.schedule(task, TimeSegundos);
        }catch (Exception e){
            System.err.println("Error "+e.getMessage());
        }
    }

    @FXML
    void btnManoDerechaEvent(ActionEvent event) {
        rbtDerecha.setSelected(true);
        rbtIzquirda.setSelected(false);
        if(client != null) client.setNameHand("ManoDerecha");
        displayImageSelected( "ManoDerecha.png","Mano");
    }

    @FXML
    void btnManoIzquierdaEvent(ActionEvent event) {
        rbtDerecha.setSelected(false);
        rbtIzquirda.setSelected(true);
        if(client != null) client.setNameHand("ManoIzquierda");
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
    public void displayMessage(String Message) {
        try {
            // System.out.println("displayMessage "+Message);
            Platform.runLater(() -> lblMessageProcesos.setText(Message));
            lblMessageProcesos.setFocusTraversable(true);
        }catch (Exception e){
            MostrarAlertaBorrar("Error","APP,displayMessage: Error al Mostrar Mensaje ");
            System.err.println("APP,displayMessage: Error al Mostrar Mensaje ");
        }


    }
    public void  ActivarCaptura(boolean Activar){
        if(!Activar) huella.start();
        btnIniciar.setDisable(Activar);
        btnGuardar.setDisable(!Activar);
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
        btnIniciar.setDisable(true);
        btnGuardar.setDisable(true);
       // huella.start(); Agosto 4

    }

}