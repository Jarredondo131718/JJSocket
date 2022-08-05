/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.visualsmic.vshuellassocket.Services;

import com.visualsmic.vshuellassocket.Models.User;
import com.visualsmic.vshuellassocket.App;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 *
 * @author visua
 */
public class Login {
    public Login() {
    }

    public boolean LoginIN(String UserName, String Password) {

        String Caracter = "\"";
        String inputJson = "{ \"strUsuario\":";
        inputJson += Caracter + UserName + Caracter + ",";
        inputJson += Caracter + "strClave" + Caracter + ":" + Caracter + Password + Caracter + ",";

        inputJson += Caracter + "ExecuteQuery" + Caracter + ":" + Caracter + "QRY" + Caracter + ",";
        inputJson += "\"Formula\":" + Caracter + "LoadUserJava" + Caracter + " }";

        VSConsumeRest HR = new VSConsumeRest();
        try {

        JSONObject RS = HR.ReturnServices(inputJson);

            if(RS==null){ return false; }

            User user = new User(RS.getInt("lngNumId"), RS.getInt("intPrioridad"),
                    RS.getString("strNit"), RS.getString("NombreUsuario"),
                    RS.getString("NombreOficina"), RS.getString("SOINode"),
                    RS.getString("SOIJava"));
            FileManagement FM = new FileManagement();
            FM.FileManagement(user);

            if(user.getLngNumId()>0){
                try {
                    App.setRoot("frmEnrollment");
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }
        } catch (JSONException e) {

            System.err.println("Error en Login " + e.getMessage());
            return false;
        }
        return false;

    }
}
