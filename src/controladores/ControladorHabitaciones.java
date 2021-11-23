/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOHabitacion;
import formularios.FormHabitacion;
import formularios.componentes.FormHabitacionesItems;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.SwingUtilities;
import modelos.Habitacion;

/**
 *
 * @author Alex
 */
public class ControladorHabitaciones {

    private Habitacion habitacion;
    private static FormHabitacion vista;
    private static DAOHabitacion daoHabitacion;
    private static List<Habitacion> listaHabitaciones = new ArrayList<>();

    /*CONSTRUCTORES*/
    public ControladorHabitaciones() {

    }

    public ControladorHabitaciones(FormHabitacion vista) {
        this.vista = vista;
        daoHabitacion = new DAOHabitacion();

        //Incializar componentes
        iniComponentes();

        //Inicializar la lista con los registros de habitaciones de la BD
        setListaHabitaciones(daoHabitacion.obtenerHabitaciones());

        //Cargamos la lista anterior en el panel de habitaciones
        cargarPanelHabitaciones();
    }

    /*METODOS*/
    private void iniComponentes() {
        vista.getPanelNuevaHabitacion().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    /*Función para volver a actualizar la lista desde la BD
    e imprimirla en la vista*/
    public static void actualizarPanelHabitaciones() {
        setListaHabitaciones(daoHabitacion.obtenerHabitaciones());
        cargarPanelHabitaciones();
    }

    /*Método para obtener únicamente los Id's de las habitaciones disponibles,
    utilizado por ejemplo por el jcombobox para reservar una habitacion*/
    public static List<String> getListHabitacionesDisponiblesIDs() {
        List<String> lista = new ArrayList<>();

        for (int i = 0; i < listaHabitaciones.size(); i++) {
            if (listaHabitaciones.get(i).getEstatus().equalsIgnoreCase("Disponible")) {
                lista.add(String.valueOf(listaHabitaciones.get(i).getId_habitacion()));
            }
        }
        return lista;
    }

    /*Método que carga los registros de habitaciones en el Jpanel*/
    public static void cargarPanelHabitaciones() {

        vista.getPanelHabitacionesItems().removeAll();
        vista.getPanelHabitacionesItems().add(Box.createRigidArea(new Dimension(5, 15)));
        for (Habitacion habitacion : listaHabitaciones) {
            FormHabitacionesItems formHabitacionesItems = new FormHabitacionesItems(habitacion);

            SwingUtilities.invokeLater(() -> {
                vista.getPanelHabitacionesItems().add(formHabitacionesItems);
                vista.getPanelHabitacionesItems().add(Box.createRigidArea(new Dimension(5, 15)));
            });

        }
        vista.getPanelHabitacionesItems().revalidate();
    }

    /*GETTER Y SETTERS*/
    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public static void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        ControladorHabitaciones.listaHabitaciones = listaHabitaciones;
    }

}
