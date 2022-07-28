/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author visua
 */
public class Client {
    public int lngNumIdClient;
    private String NitClient;
    private String NameHand;
    private String NameFinger;
    private String NameClient;
    private  String ImageHuella;
    private String NombreOficina;
    public  String Huella;
    private int CodigoOficina;
    private  String FormulaAction;
    public Client(int lngNumIdClient, String nitClient, String nameHand, String nameFinger, String nameClient, String imageHuella,
                  String nombreOficina, String huella, int codigoOficina, String FormulaAction) {
        this.lngNumIdClient = lngNumIdClient;
        this.NitClient = nitClient;
        this.NameHand = nameHand;
        this.NameFinger = nameFinger;
        this.NameClient = nameClient;
        this.ImageHuella = imageHuella;
        this.NombreOficina = nombreOficina;
        this.Huella = huella;
        this.CodigoOficina = codigoOficina;
        this.FormulaAction = FormulaAction;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" +
                "lngNumIdClient=" + lngNumIdClient +
                ", NitClient='" + NitClient + '\'' +
                ", NameHand='" + NameHand + '\'' +
                ", NameFinger='" + NameFinger + '\'' +
                ", NameClient='" + NameClient + '\'' +
                ", ImageHuella='" + ImageHuella + '\'' +
                ", NombreOficina='" + NombreOficina + '\'' +
                ", Huella='" + Huella + '\'' +
                ", CodigoOficina=" + CodigoOficina +
                ", FormulaAction='" + FormulaAction + '\'' +
                '}';
    }

    public int getLngNumIdClient() {
        return lngNumIdClient;
    }

    public void setLngNumIdClient(int lngNumIdClient) {
        this.lngNumIdClient = lngNumIdClient;
    }

    public String getNitClient() {
        return NitClient;
    }

    public void setNitClient(String nitClient) {
        NitClient = nitClient;
    }

    public String getNameHand() {
        return NameHand;
    }

    public void setNameHand(String nameHand) {
        NameHand = nameHand;
    }

    public String getNameFinger() {
        return NameFinger;
    }

    public void setNameFinger(String nameFinger) {
        NameFinger = nameFinger;
    }

    public String getNameClient() {
        return NameClient;
    }

    public void setNameClient(String nameClient) {
        NameClient = nameClient;
    }

    public String getImageHuella() {
        return ImageHuella;
    }

    public void setImageHuella(String imageHuella) {
        ImageHuella = imageHuella;
    }

    public String getNombreOficina() {
        return NombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        NombreOficina = nombreOficina;
    }

    public String getHuella() {
        return Huella;
    }

    public void setHuella(String huella) {
        Huella = huella;
    }

    public int getCodigoOficina() {
        return CodigoOficina;
    }

    public void setCodigoOficina(int codigoOficina) {
        CodigoOficina = codigoOficina;
    }

    public String getFormulaAction() {
        return FormulaAction;
    }

    public void setFormulaAction(String formulaAction) {
        FormulaAction = formulaAction;
    }
}
