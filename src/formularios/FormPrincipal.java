/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import controladores.ControladorHabitaciones;
import controladores.ControladorReservaciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jiconfont.swing.IconFontSwing;
import modelos.Habitacion;
import modelos.Reservacion;
import recursos.iconos.GoogleMaterialDesignIcons;
import utiles.Conexion;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class FormPrincipal extends javax.swing.JFrame {

    List<Habitacion> listaHabitaciones = new ArrayList<>();
    List<Reservacion> listaReservaciones = new ArrayList<>();
    DefaultTableModel modelTablaReservaciones;

    /**
     * Creates new form formPrincipal
     */
    public FormPrincipal() {
        setupFormPrincipal();
        initComponents();
        pintarIconos();
        setLocationRelativeTo(null);
        setupTablaReservaciones();
    }

    /*Método para color iconos en la ventana principal*/
    private void pintarIconos() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        //Top bar
        lblIconSalir.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.CLOSE, 30, Constantes.COLOR_BLANCO));
        lblSistemaIco.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DASHBOARD, 25, Constantes.COLOR_BLANCO));
        lblUsuarioIcon.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ACCOUNT_CIRCLE, 20, Constantes.COLOR_BLANCO));
        lblNotificacionIcon.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.NOTIFICATIONS, 20, Constantes.COLOR_BLANCO));
        lblCorazonIcon.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE, 20, Constantes.COLOR_BLANCO));

        //Accesos rápidos
        lblNuevaReservacionIcono.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_CIRCLE, 30, new Color(95, 95, 95)));
        lblNuevaHabitacion.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.WEEKEND, 30, new Color(95, 95, 95)));

    }

    /*Método para configurar la ventana principal*/
    private void setupFormPrincipal() {
        //Ajustar al 90% de la pantalla
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        int gameHeight = (int) (Math.round(ySize * 0.90));
        int gameWidth = (int) (Math.round(xSize * 0.90));
        setPreferredSize(new Dimension(gameWidth, gameHeight));

        getContentPane().setBackground(new Color(230, 230, 230));
        setResizable(true);
        setUndecorated(true);

        //Redondear bordes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
            }
        });
    }

    /*Método que carga los registros de habitaciones en el Jpanel*/
    public void cargarPanelHabitaciones() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                panelHabitacionesItems.removeAll();
                panelHabitacionesItems.add(Box.createRigidArea(new Dimension(5, 15)));
                for (Habitacion habitacion : listaHabitaciones) {
                    FormHabitacionesItems formHabitacionesItems = new FormHabitacionesItems(habitacion);
                    panelHabitacionesItems.add(formHabitacionesItems);
                    panelHabitacionesItems.add(Box.createRigidArea(new Dimension(5, 15)));
                }
                panelHabitacionesItems.revalidate();
            }
        });
        t1.start();
    }

    public void cargarTablaReservaciones() {
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
        tablaReservaciones.setModel(modelTablaReservaciones);

        //Ancho de algunas columnas
        tablaReservaciones.getColumnModel().getColumn(0).setMinWidth(50);
        tablaReservaciones.getColumnModel().getColumn(3).setMinWidth(60);
        tablaReservaciones.getColumnModel().getColumn(4).setMinWidth(10);

        //Altura de las filas
        tablaReservaciones.setRowHeight(30);

        //Ajustar contenido
        tablaReservaciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    /*Getter y Setters*/
    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    public List<Reservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    public void setListaReservaciones(List<Reservacion> listaReservaciones) {
        this.listaReservaciones = listaReservaciones;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelToolBar = new JPanelArrastrable();
        lblIconSalir = new javax.swing.JLabel();
        lblSistemaNombre = new javax.swing.JLabel();
        lblSistemaIco = new javax.swing.JLabel();
        lblCorazonIcon = new javax.swing.JLabel();
        lblUsuarioIcon = new javax.swing.JLabel();
        lblNotificacionIcon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaReservaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelToolBar.setBackground(Constantes.COLOR_PRIMARIO);
        panelToolBar.setPreferredSize(new java.awt.Dimension(900, 50));

        lblIconSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconSalir.setAlignmentX(0.5F);
        lblIconSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblIconSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconSalirMouseClicked(evt);
            }
        });

        lblSistemaNombre.setText(Constantes.EMPRESA);
        lblSistemaNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSistemaNombre.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelToolBarLayout = new javax.swing.GroupLayout(panelToolBar);
        panelToolBar.setLayout(panelToolBarLayout);
        panelToolBarLayout.setHorizontalGroup(
            panelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelToolBarLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblSistemaIco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSistemaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 394, Short.MAX_VALUE)
                .addComponent(lblCorazonIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblNotificacionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblUsuarioIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblIconSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelToolBarLayout.setVerticalGroup(
            panelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelToolBarLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblSistemaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSistemaIco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCorazonIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuarioIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNotificacionIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(panelToolBar, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 450));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(230, 230, 230));

        panelNuevaReservacion.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 80, 80));
            }
        });
        panelNuevaReservacion.setBackground(new Color(240,240,240));
        panelNuevaReservacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelNuevaReservacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelNuevaReservacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelNuevaReservacionMouseExited(evt);
            }
        });
        panelNuevaReservacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNuevaReservacionIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelNuevaReservacion.add(lblNuevaReservacionIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 40));

        jLabel2.setForeground(new java.awt.Color(87, 87, 87));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nueva reservación");
        panelNuevaReservacion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 120, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Accesos rápidos");

        panelNuevaReservacion.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 80, 80));
            }
        });
        panelNuevaHabitacion.setBackground(new Color(240,240,240));
        panelNuevaHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelNuevaHabitacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelNuevaHabitacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelNuevaHabitacionMouseExited(evt);
            }
        });
        panelNuevaHabitacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNuevaHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelNuevaHabitacion.add(lblNuevaHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 40));

        jLabel3.setForeground(new java.awt.Color(87, 87, 87));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Nueva habitacion");
        panelNuevaHabitacion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 120, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelNuevaReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelNuevaHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNuevaReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNuevaHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));

        jScrollPane1.setBackground(null);
        jScrollPane1.getViewport().setBackground(new Color(250,250,250));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelHabitacionesItems.setBackground(new java.awt.Color(250, 250, 250));
        panelHabitacionesItems.setLayout(new javax.swing.BoxLayout(panelHabitacionesItems, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelHabitacionesItems);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(230, 230, 230));

        jScrollPane2.getViewport().setBackground(Constantes.COLOR_GRIS);
        jScrollPane2.setBorder(null);

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
        tablaReservaciones.setSelectionBackground(Constantes.COLOR_PRIMARIO);
        jScrollPane2.setViewportView(tablaReservaciones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblIconSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblIconSalirMouseClicked

    private void panelNuevaReservacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNuevaReservacionMouseEntered
        panelNuevaReservacion.setBackground(new Color(245, 245, 245));
    }//GEN-LAST:event_panelNuevaReservacionMouseEntered

    private void panelNuevaReservacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNuevaReservacionMouseExited
        panelNuevaReservacion.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_panelNuevaReservacionMouseExited

    private void panelNuevaReservacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNuevaReservacionMouseClicked
        JFrame frame = this;
        new FormCrearReservacion(frame, true).show();
    }//GEN-LAST:event_panelNuevaReservacionMouseClicked

    private void panelNuevaHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNuevaHabitacionMouseClicked
        JFrame frame = this;
        new FormCrearHabitacion(frame, true).show();
    }//GEN-LAST:event_panelNuevaHabitacionMouseClicked

    private void panelNuevaHabitacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNuevaHabitacionMouseEntered
        panelNuevaHabitacion.setBackground(new Color(245, 245, 245));
    }//GEN-LAST:event_panelNuevaHabitacionMouseEntered

    private void panelNuevaHabitacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNuevaHabitacionMouseExited
        panelNuevaHabitacion.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_panelNuevaHabitacionMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Mostrar ventana principal
                FormPrincipal formPrincipal = new FormPrincipal();
                formPrincipal.setVisible(true);

                /*Inicializar conexion a la DB, inicializar controladores*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Conexion a = new Conexion();
                        a.inicializarProps();
                        ControladorReservaciones ctrlReservaciones = new ControladorReservaciones(formPrincipal);
                        ControladorHabitaciones ctrlHabitaciones = new ControladorHabitaciones(formPrincipal);
                    }
                }).start();

                //Ajustar al tamaño máximo de la pantalla
                //formPrincipal.setExtendedState(formPrincipal.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCorazonIcon;
    private javax.swing.JLabel lblIconSalir;
    private javax.swing.JLabel lblNotificacionIcon;
    private javax.swing.JLabel lblNuevaHabitacion;
    private javax.swing.JLabel lblNuevaReservacionIcono;
    private javax.swing.JLabel lblSistemaIco;
    private javax.swing.JLabel lblSistemaNombre;
    private javax.swing.JLabel lblUsuarioIcon;
    private javax.swing.JPanel panelHabitacionesItems;
    private javax.swing.JPanel panelNuevaHabitacion;
    private javax.swing.JPanel panelNuevaReservacion;
    private javax.swing.JPanel panelToolBar;
    private javax.swing.JTable tablaReservaciones;
    // End of variables declaration//GEN-END:variables

}
