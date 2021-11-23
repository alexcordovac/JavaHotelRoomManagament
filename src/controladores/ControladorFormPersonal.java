/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOEmpleado;
import formularios.FormPersonal;
import formularios.componentes.EmpleadoItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelos.Empleado;

/**
 *
 * @author Alex
 */
public class ControladorFormPersonal {

    public static FormPersonal vista;
    public static List<Empleado> listaEmpleados = new ArrayList();
    private static DAOEmpleado daoEmpleado = new DAOEmpleado();

    /*CONSTRUCTORES*/
    public ControladorFormPersonal() {
    }

    public ControladorFormPersonal(FormPersonal vista) {
        ControladorFormPersonal.vista = vista;
        iniComponentes();

        //Actualizamos la lista y la pintamos
        actualizarEmpleados();
    }

    /*MÉTODOS*/
 /*Método para inicializar componentes de la vista*/
    public void iniComponentes() {
        /*Botón buscar*/
        vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Empleado> filtrada = filtrarLista(vista.getTxtBusqueda().getText());
                pintarListaEmpleados(filtrada);
            }
        });

        /*Al hacer enter en el jtextfield de búsqueda*/
        vista.getTxtBusqueda().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.getBtnBuscar().doClick();
            }
        });

        /*Botón registrar*/
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Si hay campos vacíos
                if (vista.getTxtNombre().getText().isEmpty() || vista.getTxtApellidos().getText().isEmpty()
                        || vista.getTxtEdad().getText().isEmpty() || vista.getTxtPuesto().getText().isEmpty()
                        || vista.getTxtSueldo().getText().isEmpty() || vista.getTxtArea().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Hay campos vacíos", "Error al registrar", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //Creamos el empleado
                Empleado emp = null;
                try {
                    String nombre = vista.getTxtNombre().getText();
                    String apellidos = vista.getTxtApellidos().getText();
                    byte edad = Byte.parseByte(vista.getTxtEdad().getText());
                    String puesto = vista.getTxtPuesto().getText();
                    int sueldo = Integer.parseInt(vista.getTxtSueldo().getText());
                    String area = vista.getTxtArea().getText();
                    emp = new Empleado(nombre, apellidos, edad, puesto, sueldo, area);
                } catch (NumberFormatException exc) {
                    System.err.println("NumberFormatException al registrar empleado.");
                    JOptionPane.showMessageDialog(null, "NumberFormatException: no se pudo convertir "
                            + "la edad o el sueldo, verifique los datos ingresados",
                            "Error de conversión de datos", JOptionPane.WARNING_MESSAGE);
                    exc.printStackTrace();
                    return;
                }

                //Lo agregamos a la BD
                int filas = daoEmpleado.registrarEmpleado(emp);
                if (filas > 0) {
                    JOptionPane.showMessageDialog(null, "¡El registro fue exitoso!",
                            "Agregado correctamente", JOptionPane.INFORMATION_MESSAGE);
                    limpiarDatosEmpleado();
                    actualizarEmpleados();
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se realizó el registro!",
                            "Lo sentimos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /*Método para limpiar los campos de texto de registrar empleado*/
    public void limpiarDatosEmpleado() {
        vista.getTxtNombre().setText("");
        vista.getTxtApellidos().setText("");
        vista.getTxtEdad().setText("");
        vista.getTxtPuesto().setText("");
        vista.getTxtSueldo().setText("");
        vista.getTxtArea().setText("");
    }

    /*Método que se encarga de pintar una lista en el panel de empleados 
    (la vista es el FormPersonal el panel de abajo)*/
    public static void pintarListaEmpleados(List<Empleado> lista) {

        //Eliminamos todo del panel
        vista.getPanelPersonal().removeAll();
        vista.getPanelPersonal().revalidate();
        vista.getPanelPersonal().repaint();

        //Si la lista a pintar está vacía
        if (lista.size() <= 0) {
            vista.getPanelPersonal().add(new JLabel("Sin elementos"));
            return;
        }

        //Si la lista no está vacía, los pintamos
        for (int i = 0; i < lista.size(); i++) {
            EmpleadoItem empleadoItem = new EmpleadoItem(lista.get(i));
            vista.getPanelPersonal().add(empleadoItem);
        }
    }

    /*Método para filtrar la lista de empleados de acuerdo a un parámetro de búsqueda*/
    public static List<Empleado> filtrarLista(String busqueda) {
        List<Empleado> filtrada = listaEmpleados.stream().filter(em -> eliminarAcentos(em.toString().toLowerCase()).indexOf(busqueda.toLowerCase()) > -1).collect(Collectors.toList());
        return filtrada;
    }

    /*Método para cambiar caracteres especiales por caracteres normales,
    este método lo utilizo para poder filtrar la lista*/
    public static String eliminarAcentos(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    /*Método para actualizar la lista de empleados recuperándola de la base de datos*/
    public static void actualizarListaEmpleados() {
        listaEmpleados = daoEmpleado.obtenerListaEmpleados();
    }

    /*Método para actualizar tanto la lista de empleados como la vista*/
    public static void actualizarEmpleados() {
        actualizarListaEmpleados();
        pintarListaEmpleados(listaEmpleados);
    }

    public static void eliminarEmpleado(Empleado em) {
        int filas = daoEmpleado.eliminarEmpleado(em.getIdEmpleado());
        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "¡Empleado eliminado correctamente!",
                    "Éxito al eliminar", JOptionPane.INFORMATION_MESSAGE);
            listaEmpleados.remove(em);
            pintarListaEmpleados(listaEmpleados);
        } else {
            JOptionPane.showMessageDialog(null, "¡No se eliminó el empleado!",
                    "Lo sentimos", JOptionPane.ERROR_MESSAGE);
        }
    }

}
