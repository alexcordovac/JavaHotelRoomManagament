/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import controladores.ControladorAccesosRapidos;
import controladores.ControladorHabitaciones;
import controladores.ControladorReservaciones;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class FormHabitacion extends javax.swing.JPanel {

    /**
     * Creates new form FormHabitacion
     */
    public FormHabitacion() {
        initComponents();
        pintarIconos();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);

        //Controlador de los accesos rápidos
        ControladorAccesosRapidos controladorAccesosRapidos = new ControladorAccesosRapidos(this);

        //Controlador de las habitaciones
        ControladorHabitaciones ctrlHabitaciones = new ControladorHabitaciones(this);

        //Controlador de las reservaciones
        ControladorReservaciones controladorReservaciones = new ControladorReservaciones(this);
    }

    /*Método para color iconos en la ventana principal*/
    private void pintarIconos() {
        //Accesos rápidos
        lblNuevaReservacionIcono.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_CIRCLE, 30, new Color(95, 95, 95)));
        lblNuevaHabitacion.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.WEEKEND, 30, new Color(95, 95, 95)));

    }

    /*GETTER Y SETTERS*/
    //Habitaciones
    public JPanel getPanelHabitacionesItems() {
        return panelHabitacionesItems;
    }

    //Accesos rápidos
    public JPanel getPanelNuevaHabitacion() {
        return panelNuevaHabitacion;
    }

    public JPanel getPanelNuevaReservacion() {
        return panelNuevaReservacion;
    }

    //Reservaciones
    public JTable getTablaReservaciones() {
        return tablaReservaciones;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelNuevaReservacion = new javax.swing.JPanel();
        lblNuevaReservacionIcono = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelNuevaHabitacion = new javax.swing.JPanel();
        lblNuevaHabitacion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelHabitacionesItems = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaReservaciones = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(375, 550));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setOpaque(false);

        panelNuevaReservacion.setBackground(new Color(240,240,240));
        panelNuevaReservacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNuevaReservacionIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelNuevaReservacion.add(lblNuevaReservacionIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nueva reservación");
        panelNuevaReservacion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 110, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Accesos rápidos");

        panelNuevaHabitacion.setBackground(new Color(240,240,240));
        panelNuevaHabitacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNuevaHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelNuevaHabitacion.add(lblNuevaHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Nueva habitacion");
        panelNuevaHabitacion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 100, 20));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(panelNuevaReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelNuevaHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNuevaReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNuevaHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setOpaque(false);

        jScrollPane1.setBackground(null);
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        panelHabitacionesItems.setBackground(new java.awt.Color(250, 250, 250));
        panelHabitacionesItems.setOpaque(false);
        panelHabitacionesItems.setLayout(new javax.swing.BoxLayout(panelHabitacionesItems, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelHabitacionesItems);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
        );

        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setViewportBorder(null);
        JScrollBar vertical1 = jScrollPane1.getVerticalScrollBar();
        vertical1.setPreferredSize( new Dimension(0,0) );

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(230, 230, 230));
        jPanel7.setOpaque(false);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        tablaReservaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaReservaciones.setOpaque(false);
        tablaReservaciones.setSelectionBackground(Constantes.COLOR_PRIMARIO);
        jScrollPane2.setViewportView(tablaReservaciones);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setViewportBorder(null);
        JScrollBar vertical2 = jScrollPane2.getVerticalScrollBar();
        vertical2.setPreferredSize( new Dimension(0,0) );

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNuevaHabitacion;
    private javax.swing.JLabel lblNuevaReservacionIcono;
    private javax.swing.JPanel panelHabitacionesItems;
    private javax.swing.JPanel panelNuevaHabitacion;
    private javax.swing.JPanel panelNuevaReservacion;
    private javax.swing.JTable tablaReservaciones;
    // End of variables declaration//GEN-END:variables
}