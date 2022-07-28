/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import FIngerUtils.CapturarHuella;
import Models.Client;
import Models.User;




import java.io.IOException;

import java.net.URI;

import com.visualsmic.vshuellassocket.App;
import org.json.JSONException;
import org.json.JSONObject;


import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 *
 * @author visua
 */
public class VSSocket {

    String url = "http://localhost:4000";
    User user;
    private Socket SO;
    private final FileManagement FM = new FileManagement();

    public VSSocket() {
//        FileManagement FM = new FileManagement();
        this.user = FM.ReadFileConfig();
    }

    public void ConnectSocket() {
        System.out.println("Services.VSSocket.ConnectSocket() 1");

        JSONObject object = new JSONObject();
        object.put("IdUser", user.getLngNumId());
        System.out.println("Entrando a ConnectSocket 2 " + user + " gueva " + user.getLngNumId());
        try {
            IO.Options options = IO.Options.builder()
                    .setForceNew(false)
                    .build();
            // Number of failed retries
            options.reconnectionAttempts = 10;
            // Time interval for failed reconnection
            options.reconnectionDelay = 1000;
            // Connection timeout (ms)
            options.timeout = 500;
            System.out.println("Services.VSSocket.ConnectSocket() a conectarme io.socket");
            SO = IO.socket(URI.create(url), options); // the main namespace
            System.out.println("Services.VSSocket.ConnectSocket() a conectarme");
            SO.connect();
            System.out.println("Services.VSSocket.ConnectSocket() a me conectane");
            sendMsg("LoginJava", object);

            SO.on("Enrollment", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Evento Socket Enrrolement a ObtenerClaseClient "+args[0]);
                    ObtenerClaseClient((JSONObject) args[0]);
                   // try {
                       // App.setRoot("frmEnrollment");
                  //  } catch (IOException e) {
                 //       System.err.println("Evento Socket Enrrolement "+e.getMessage());
                        //throw new RuntimeException(e);
                 //   }
                }
            });

        } catch (JSONException ex) {
            System.out.println("Error Services.VSSocket.ConnectSocket()" + ex.getMessage());
            System.out.println("Error Services.VSSocket.ConnectSocket()" + ex.getCause());
//            Logger.getLogger(VSSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DesconnetSocke() {
    }

    private void ObtenerClaseClient(JSONObject RS) {

        Client client = new Client(RS.getInt("lngNumIdClient"), RS.getString("NitClient"),"Derecha","Indice",
                RS.getString("NameClient"),"ImageHuella", RS.getString("OficinaClientName"),"Huella" ,
                RS.getInt("OficinaClientCode"),RS.getString("FormulaAction"));
        System.out.println("Este es el RS "+RS);
        System.out.println("asi queda liente "+client);
        try {
            FM.GenerarFileClient(client);
            System.out.println("ObtenerClaseClient SOCKET FM.ReadFileClient() " + FM.ReadFileClient()); //esta no va, va en el frmcliente
        } catch (IOException ex) {
//            Logger.getLogger(VSSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Obtener CLse " + client);

    }

    public void sendMsg(String nameEven, JSONObject object) {

        SO.emit(nameEven, object);

        Emitter on = SO.on(nameEven, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Mensaje Recibido Del Servidor" + args[0]);

                try {
                    AnalisisSendmsgRespond("LoginJava", (JSONObject) args[0]);
                } catch (JSONException ex) {
                    System.err.println("ERROR, Socket VS : " + ex.getMessage());
//                    Logger.getLogger(ProbarSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void AnalisisSendmsgRespond(String FormulMsg, JSONObject RS) {
        System.out.println(".AnalisisSendmsgRespond() " + RS);
        switch (FormulMsg) {
            case "LoginJava": {
                user.setSOIJava(RS.getString("idSocket"));
                try {
                    FM.GenerarFileString(user);
                    System.out.println("ReadJSonLogin,GenerarFileString  user " + user);
                } catch (IOException ex) {
                    System.out.println(".AnalisisSendmsgRespond() LoginJava" + ex.getMessage());
//                    Logger.getLogger(VSSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(".AnalisisSendmsgRespond() LoginJava" + RS);
            }
            default:
                // System.out.println(".AnalisisSendmsgRespond() LoginJava Por  default: " + FormulMsg);
                /* throw new AssertionError(); */
        }
    }
}
