/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOHabitacion;
import formularios.FormPrincipal;
import java.util.ArrayList;
import java.util.List;
import modelos.Habitacion;
import modelos.Reservacion;

/**
 *
 * @author Alex
 */
public class ControladorHabitaciones {
    private Habitacion habitacion;
    private static FormPrincipal vista;
    private static DAOHabitacion daoHabitacion;

    public ControladorHabitaciones() {
        
    }

    public ControladorHabitaciones(FormPrincipal vista) {
        this.vista = vista;
        daoHabitacion = new DAOHabitacion();
        setHabitaciones();
        vista.cargarPanelHabitaciones();
    }
    
    public static void setHabitaciones() {
        vista.setListaHabitaciones(daoHabitacion.obtenerHabitaciones());
    }
    
    public  static void actualizarPanelHabitaciones(){
        setHabitaciones();
        vista.cargarPanelHabitaciones();
    }
    
    public static List<String> getListHabitacionesDisponiblesIDs(){
        List<Habitacion> listaHabitaciones = vista.getListaHabitaciones();
        List<String> lista = new ArrayList<>();
        
        for (int i = 0; i < listaHabitaciones.size(); i++) {
            if (listaHabitaciones.get(i).getEstatus().equalsIgnoreCase("Disponible")){
                lista.add(String.valueOf(listaHabitaciones.get(i).getId_habitacion()));
            }
        }
        return lista;
    }
}
