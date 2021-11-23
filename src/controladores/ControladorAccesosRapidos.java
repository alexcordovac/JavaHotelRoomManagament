/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import formularios.FormCrearHabitacion;
import formularios.FormCrearReservacion;
import formularios.FormHabitacion;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class ControladorAccesosRapidos {

    private static FormHabitacion vista;

    public ControladorAccesosRapidos() {
    }

    public ControladorAccesosRapidos(FormHabitacion vista) {
        ControladorAccesosRapidos.vista = vista;
        iniComponentes();
    }

    private void iniComponentes() {
        vista.getPanelNuevaHabitacion().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FormCrearHabitacion(null, true).setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panelOnHover(vista.getPanelNuevaHabitacion());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelOnHoverLeft(vista.getPanelNuevaHabitacion());
            }
        });

        vista.getPanelNuevaReservacion().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FormCrearReservacion(null, true).setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panelOnHover(vista.getPanelNuevaReservacion());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelOnHoverLeft(vista.getPanelNuevaReservacion());
            }
        });

    }
    
    private void panelOnHover(JPanel panel){
        panel.setBackground(new Color(245, 245, 245));
    }
    
    private void panelOnHoverLeft(JPanel panel){
        panel.setBackground(new Color(240, 240, 240));
    }

}
