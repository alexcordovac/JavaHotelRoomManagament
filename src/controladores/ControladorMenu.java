/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import formularios.FormHabitacion;
import formularios.FormPersonal;
import formularios.FormPrincipal;
import formularios.componentes.MenuItem;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import utiles.Constantes;
import java.util.HashMap;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;

/**
 * Controla la vista del cuerpo de la pantalla principal.
 * 1-Primera implementacion guardando las vistas en un hashmap, revalidando y repintando el cuerpo.
 * 2-(actual) Usando cardlayout en el cuerpo de la pantalla principal.
 *   Para eliminar esta opcion, borrar las lineas con el comentario test
 *   Opcionalmente cambiar el layout del panel cuerpo a grid layout con 1 columna
 *   Descomentar aquí en el mouselistener donde se llama el metodo controlarVista
 *   Descomentar en el método controlarVista las líneas comentadas
 * @author Alex
 */
public class ControladorMenu {

    private final FormPrincipal vista;
    private HashMap<MenuItem, JPanel> listaMenus;
    private MenuItem menuSeleccionado;

    public ControladorMenu(FormPrincipal vista) {
        this.vista = vista;
        iniMenus();
    }

    /*Función para crear y pintar los menus en el panelMenus de la vista principal*/
    private void iniMenus() {
        //listaMenus = new HashMap<>();
        
        //Menu items
        MenuItem menuHabitacion = new MenuItem(GoogleMaterialDesignIcons.HOME, "Habitacion");
        MenuItem menuPersonal = new MenuItem(GoogleMaterialDesignIcons.PERSON, "Personal");
        
        //Vistas
        FormHabitacion formHabitacion = new FormHabitacion();
        FormPersonal formPersonal = new FormPersonal();
        
        //Los guardamos en el hashmap con su respectivo formulario
        //menusVistas.put(asignarTrabajo, panelTrabajadorLayout);
       //menusVistas.put(recordsObtenidos , formRecordObtenidos);
        
        //Pintamos la lista de menus
        pintarMenuItems(menuHabitacion, menuPersonal);
        
        //test
        vista.getPanelCuerpo().add(formHabitacion, menuHabitacion.getName());
        vista.getPanelCuerpo().add(formPersonal, menuPersonal.getName());
        
        //test
        //Pinto de color este menu (clickedao) porque es el que se muestra por default al iniciar
        menuHabitacion.setColor();
        
        
        menuSeleccionado = menuHabitacion;
    }
    
    private void pintarMenuItems(MenuItem... menu) {
        MouseListener mouseListener = iniMouseListener();
        for (int i = 0; i < menu.length; i++) {
            vista.getPanelMenus().add(menu[i]);
            menu[i].addMouseListener(mouseListener);
        }
        vista.getPanelMenus().revalidate();
        vista.getPanelMenus().repaint();
    }

    private MouseListener iniMouseListener() {
        MouseListener mouseListenerPanel = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlarVista(e);
                
                //test
                ((CardLayout) vista.getPanelCuerpo().getLayout()).show(vista.getPanelCuerpo(), ((MenuItem) e.getSource()).getName());
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
        };

        return mouseListenerPanel;
    }

    private void controlarVista(MouseEvent e) {
        MenuItem tmp = (MenuItem) e.getSource();

        if (tmp != this.menuSeleccionado) {

            if (this.menuSeleccionado != null) {
                this.menuSeleccionado.resetColor();
            }

            tmp.setColor();
            this.menuSeleccionado = tmp;

            //Si el menu ya está registrado en el hashmap con su JPanel lo traemos y lo pintamos
//            JPanel hashFound = this.menusVistas.get(tmp);
//            if (hashFound != null) {
//                JPanel panel = vista.getPanelCuerpo();
//                panel.removeAll();
//                panel.add(hashFound);
//                panel.repaint();
//                panel.revalidate();
//            }
        }
    }

}
