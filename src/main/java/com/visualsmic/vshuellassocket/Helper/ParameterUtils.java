package com.visualsmic.vshuellassocket.Helper;

import com.visualsmic.vshuellassocket.Models.Client;
import com.visualsmic.vshuellassocket.Services.FileManagement;
import com.visualsmic.vshuellassocket.EnrollmentController;
import java.util.Timer;
import java.util.TimerTask;

public class ParameterUtils {
    private String Caracter = "\"";
    private String inputJson =  "{ \"lngNumId\":";

    public ParameterUtils() {
    }

    public  String ParameterDBEnrroll(){
        inputJson=  "{ \"lngNumId\":";
        FileManagement FM = new FileManagement();

        Client client = FM.ReadFileClient();
        inputJson += Caracter + client.lngNumIdClient + Caracter + ",";
        inputJson += Caracter + "strMano" + Caracter + ":" + Caracter + client.getNameHand() + Caracter + ",";
        inputJson += Caracter + "strDedo" + Caracter + ":" + Caracter + client.getNameFinger() + Caracter + ",";

        inputJson += Caracter + "ExecuteQuery" + Caracter + ":" + Caracter + "Ins" + Caracter + ",";
        inputJson += Caracter + "Formula" + Caracter + ":" + Caracter +  "AdmonHuellas" + Caracter + ",";
        inputJson += Caracter + "strImage" + Caracter + ":" + Caracter + client.getImageHuella() + Caracter + " }";
       // System.out.println("CadenaDBEnrroll asi quedo stringparametet "+inputJson);
        return  inputJson;
    }

    public  String ParameterDBConsultaHuella(){
        FileManagement FM = new FileManagement();
        inputJson=  "{ \"lngNumId\":";
        Client client = FM.ReadFileClient();
        inputJson += Caracter + client.lngNumIdClient + Caracter + ",";
        inputJson += Caracter + "ExecuteQuery" + Caracter + ":" + Caracter + "QRY" + Caracter + ",";
        inputJson += Caracter + "Formula" + Caracter + ":" + Caracter +  "AdmonHuellas" + Caracter + " ,";
        inputJson += Caracter + "strImage" + Caracter + ":" + Caracter + client.getImageHuella() + Caracter + " }";


       // System.out.println("ParameterDBConsultaHuella asi quedo stringparametet "+inputJson);
        return  inputJson;
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
}
