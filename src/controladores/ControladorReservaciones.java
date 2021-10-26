/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOReservacion;
import formularios.FormPrincipal;
import java.util.ArrayList;
import java.util.List;
import modelos.Reservacion;

/**
 *
 * @author Alex
 */
public class ControladorReservaciones {
    private Reservacion reservacion;
    private static FormPrincipal vista;
    private static DAOReservacion daoReservacion;

 
    private ControladorReservaciones(){
        daoReservacion = new DAOReservacion();
    }
    
    public ControladorReservaciones(FormPrincipal vista) {
        this();
        this.vista = vista;
        setReservaciones();
        vista.cargarTablaReservaciones();
    }
    
    public static void setReservaciones(){
        vista.setListaReservaciones(daoReservacion.obtenerReservaciones());
    }
    
    public static void actualizarTabla(){
        setReservaciones();
        vista.cargarTablaReservaciones();
    }
    
    
}
