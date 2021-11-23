/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOReservacion;
import formularios.FormHabitacion;
import formularios.FormPrincipal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Reservacion;

/**
 *
 * @author Alex
 */
public class ControladorReservaciones {

    private Reservacion reservacion;
    private static FormHabitacion vista;
    private static DAOReservacion daoReservacion;
    private static List<Reservacion> listaReservaciones = new ArrayList<>();
    private static DefaultTableModel modelTablaReservaciones;

    /*CONSTRUCTORES*/
    private ControladorReservaciones() {
    }

    public ControladorReservaciones(FormHabitacion vista) {
        this();
        this.vista = vista;
        setupTablaReservaciones();
        daoReservacion = new DAOReservacion();
        setReservaciones();
        cargarTablaReservaciones();
    }

    /*FUNCIONES*/
 /*Método para asignarle los registros de reservaciones de la DB
    a la lista de reservaciones de este controlador*/
    public static void setReservaciones() {
        setListaReservaciones(daoReservacion.obtenerReservaciones());
    }

    /*Método para actualizar la tabla de reservaciones,
    tanto la lista como la vista, primer se actualiza la lista y 
    después se manda a pintar en la tabla*/
    public static void actualizarTabla() {
        setReservaciones();
        cargarTablaReservaciones();
    }

    /*Función para cargar la lista de reservaciones en la tabla*/
    public static void cargarTablaReservaciones() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                modelTablaReservaciones.setRowCount(0);
                for (Reservacion reservacion : listaReservaciones) {
                    Object[] registro = {reservacion.getId_reservacion(), reservacion.getFecha_entrada().toString().replaceAll("T", " "),
                        reservacion.getFecha_salida().toString().replaceAll("T", " "), reservacion.getDias(), reservacion.getId_habitacion()};
                    modelTablaReservaciones.addRow(registro);
                }
            }
        });
        t1.start();
    }

    /*Método para crear el modelo de la tabla*/
    private void setupTablaReservaciones() {
        modelTablaReservaciones = new DefaultTableModel(new String[]{"Id", "Fecha entrada",
            "Fecha salida", "Dias", "Id\nHabitacion"}, 40) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //Encabezado
        vista.getTablaReservaciones().setModel(modelTablaReservaciones);

        //Ancho de algunas columnas
        vista.getTablaReservaciones().getColumnModel().getColumn(0).setMinWidth(50);
        vista.getTablaReservaciones().getColumnModel().getColumn(3).setMinWidth(60);
        vista.getTablaReservaciones().getColumnModel().getColumn(4).setMinWidth(10);

        //Altura de las filas
        vista.getTablaReservaciones().setRowHeight(30);

        //Ajustar contenido
        vista.getTablaReservaciones().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    /*GETTER Y SETTERS*/
    public List<Reservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    public static void setListaReservaciones(List<Reservacion> listaReservaciones) {
        ControladorReservaciones.listaReservaciones = listaReservaciones;
    }

}
