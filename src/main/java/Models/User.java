/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author visua
 */

public class User {


    private int lngNumId;
    private int intPrioridad;    
    public static String strNit;
    private String NombreUsuario;
    private String NombreOficina;    
    private String SOINode;
    public String SOIJava;    
  

    public int getLngNumId() {
        return lngNumId;
    }

    public void setLngNumId(int lngNumId) {
        this.lngNumId = lngNumId;
    }

    public int getIntPrioridad() {
        return intPrioridad;
    }

    public void setIntPrioridad(int intPrioridad) {
        this.intPrioridad = intPrioridad;
    }

    public String getStrNit() {
        return strNit;
    }

    public void setStrNit(String strNit) {
        User.strNit = strNit;
    }

    public  String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getNombreOficina() {
        return NombreOficina;
    }

    public void setNombreOficina(String NombreOficina) {
        this.NombreOficina = NombreOficina;
    }

    public String getSOINode() {
        return SOINode;
    }

    public void setSOINode(String SOINode) {
        this.SOINode = SOINode;
    }

    public String getSOIJava() {
        return SOIJava;
    }

    public void setSOIJava(String SOIJava) {
        this.SOIJava = SOIJava;
    }

    
    
    
    public User(int lngNumId, int intPrioridad, String strNit, String NombreUsuario, String NombreOficina, String SOINode, String SOIJava) {
        this.lngNumId = lngNumId;
        this.intPrioridad = intPrioridad;
        User.strNit = strNit;
        this.NombreUsuario = NombreUsuario;
        this.NombreOficina = NombreOficina;
        this.SOINode = SOINode;
        this.SOIJava = SOIJava;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "Users{" + "lngNumId=" + lngNumId + ", intPrioridad=" + intPrioridad + ", strNit=" + strNit + ", NombreUsuario=" + NombreUsuario + ", NombreOficina=" + NombreOficina + ", SOINode=" + SOINode + ", SOIJava=" + SOIJava + '}';
    }
    
}
