/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Alex
 */
public class Habitacion {
    
    /*Variables*/
    private int id_habitacion;
    private String tipo_habitacion;
    private short no_habitacion;
    private String estatus;
    private float costo;
    
    
    /*Constructores*/
    public Habitacion(){
    }

    public Habitacion(int id_habitacion, String tipo_habitacion, Short no_habitacion, String estatus, float costo) {
        this.id_habitacion = id_habitacion;
        this.tipo_habitacion = tipo_habitacion;
        this.no_habitacion = no_habitacion;
        this.estatus = estatus;
        this.costo = costo;
    }

    public Habitacion(String tipo_habitacion, short no_habitacion, float costo) {
        this.tipo_habitacion = tipo_habitacion;
        this.no_habitacion = no_habitacion;
        this.costo = costo;
    }
    
    /*Getters y setters*/

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public Short getNo_habitacion() {
        return no_habitacion;
    }

    public void setNo_habitacion(Short no_habitacion) {
        this.no_habitacion = no_habitacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    //tostring

    @Override
    public String toString() {
        return "Habitacion{" + "id_habitacion=" + id_habitacion + ", tipo_habitacion=" + tipo_habitacion + ", no_habitacion=" + no_habitacion + ", estatus=" + estatus + ", costo=" + costo + '}';
    }
        
}
