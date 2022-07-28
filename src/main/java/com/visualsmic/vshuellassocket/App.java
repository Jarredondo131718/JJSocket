package com.visualsmic.vshuellassocket;

import Services.VSSocket;
import Models.User;
import Services.FileManagement;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import javafx.application.Platform;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String FrmActual;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML(FrmActual), 640, 480);
        javafx.scene.image.Image image = new Image(getClass().getResourceAsStream("Huella.png"));

        stage.setTitle("Lector huella");
        stage.getIcons().add(image);
        stage.setScene(scene);

        stage.setOnCloseRequest(e -> {

            if(!FrmActual.equals("frmLogin")) return;
           
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FrmActual = fxml;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        FrmActual = "frmLogin";

        FileManagement FM = new FileManagement();
        User user = new User();
        if (FM.CheckConfigurationFile()) {
            user = FM.ReadFileConfig();
        }

        if (user.getLngNumId() == 0 || !FM.CheckConfigurationFile()) {
//            Login L = new Login("JA", "C");
            System.out.println("ES CERO LNGNUMID" );
        } else {
            FrmActual = "frmEnrollment";
                    VSSocket SVS = new VSSocket();
//
            SVS.ConnectSocket();

        }
        launch();
    }

}
