/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.time.LocalDateTime;

/**
 *
 * @author Alex
 */
public class Reservacion {
    /*Variables*/
    private int id_reservacion;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private byte dias;
    private int id_habitacion;
    
    /*Constructores*/
    public Reservacion(){
    }

    public Reservacion(int id_reservacion, LocalDateTime fecha_inicio, LocalDateTime fecha_salida, byte dias, int id_habitacion) {
        this.id_reservacion = id_reservacion;
        this.fecha_entrada = fecha_inicio;
        this.fecha_salida = fecha_salida;
        this.dias = dias;
        this.id_habitacion = id_habitacion;
    }

    public Reservacion(LocalDateTime fecha_inicio, LocalDateTime fecha_salida, byte dias, int id_habitacion) {
        this.fecha_entrada = fecha_inicio;
        this.fecha_salida = fecha_salida;
        this.dias = dias;
        this.id_habitacion = id_habitacion;
    }

    /*Getters y Setters*/
    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public byte getDias() {
        return dias;
    }

    public void setDias(byte dias) {
        this.dias = dias;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }
    
    //toString
    @Override
    public String toString() {
        return "Reservacion{" + "id_reservacion=" + id_reservacion + ", fecha_inicio=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", dias=" + dias + ", id_habitacion=" + id_habitacion + '}';
    }
}
