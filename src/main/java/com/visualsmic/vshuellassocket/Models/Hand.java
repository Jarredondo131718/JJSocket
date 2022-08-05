package com.visualsmic.vshuellassocket.Models;

import java.util.ArrayList;

public class Hand {
    private int idDedo;
    private String NombreDedo;


    public Hand(int idDedo, String NombreDedo) {
        this.idDedo = idDedo;
        this.NombreDedo = NombreDedo;
    }

    /**
     * @return int return the idDedo
     */

    public Hand() {
    }
    public int getIdDedo() {
        return idDedo;
    }

    /**
     * @param idDedo the idDedo to set
     */
    public void setIdDedo(int idDedo) {
        this.idDedo = idDedo;
    }

    /**
     * @return String return the NombreDedo
     */
    public String getNombreDedo() {
        return NombreDedo;
    }

    /**
     * @param NombreDedo the NombreDedo to set
     */
    public void setNombreDedo(String NombreDedo) {
        this.NombreDedo = NombreDedo;
    }

    public String toString(){
        return getNombreDedo();
    }

    public ArrayList<Hand> PoblarHans() {
        ArrayList<Hand> Hand = new ArrayList<>();
        Hand.add(new Hand(1, "Pulgar"));
        Hand.add(new Hand(2, "Indice"));
        Hand.add(new Hand(3, "Medio"));
        Hand.add(new Hand(4, "Anular"));
        Hand.add(new Hand(5, "Meñique"));
        return Hand;
    }
}