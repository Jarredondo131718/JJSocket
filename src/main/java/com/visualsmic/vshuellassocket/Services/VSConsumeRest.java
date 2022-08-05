/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.visualsmic.vshuellassocket.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author visua
 */
public class VSConsumeRest {
 private final HttpClient httpC = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    private final String Url = "http://localhost:";
    private final String PORT = "4000";
    private final String Router = "ExecuteSPGeneric";

    private JSONObject jsonObjectc = new JSONObject();
 
    private JSONObject VSConection(String inputJson) {
        String UrlFinal = Url + PORT + "/" + Router + "/";
        jsonObjectc= null;
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(UrlFinal))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .build();

            var response = httpC.send(request, HttpResponse.BodyHandlers.ofString());
            

            if (response.statusCode() == 200) {
                
                JSONObject jsonObject = new JSONObject(response.body());
                JSONArray jsonArray =  jsonObject.getJSONArray("data");        
             
                if (jsonArray.length() > 0) {
                     jsonObjectc = jsonArray.getJSONObject(0);
                } else {
                    System.out.println("VSConection Sin Datos ");
                }
            } else {
                System.out.println(" VSConection Status Code Por Else " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            jsonObjectc = null;
            System.out.println(" VSConection Eroror StatusCode " + ex.getMessage());
//            .getLogger(VSConsumeRest.class.getName()).log(.SEVERE, null, ex);
        }
        return jsonObjectc;

    }

    public VSConsumeRest() {
    }

    public JSONObject ReturnServices(String Parameter) {
        return  VSConection(Parameter);
//          System.out.println("Retorno ReturnServices gggg" +jsonArray);          
//        return jsonObjectc;
        
    }    
    
}
