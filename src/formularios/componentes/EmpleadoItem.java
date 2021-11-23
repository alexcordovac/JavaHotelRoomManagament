/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.componentes;

import controladores.ControladorFormPersonal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jiconfont.swing.IconFontSwing;
import modelos.Empleado;
import net.sf.jasperreports.engine.JRException;
import recursos.iconos.GoogleMaterialDesignIcons;
import reportes.ReporteEmpleado;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class EmpleadoItem extends javax.swing.JPanel {

    private Empleado empleado = new Empleado();

    /**
     * Creates new form EmpleadoItem
     */
    public EmpleadoItem(Empleado empleado) {
        this.empleado = empleado;
        initComponents();

        //Pintar iconos
        pintarIconos();

        //Pintar datos del empleado
        iniDatos();
    }

    private void pintarIconos() {
        iconoEliminar.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.CLEAR, 20, Color.RED));
        imagen.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON, 50, Constantes.COLOR_BLANCO));
        iconoNombre.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FACE, 15, Constantes.COLOR_PRIMARIO));
        iconoApellidos.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FACE, 15, Constantes.COLOR_PRIMARIO));
        iconoEdad.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FACE, 15, Constantes.COLOR_PRIMARIO));
    }

    private void iniDatos() {
        lblNombre.setText(empleado.getNombre());
        lblApellidos.setText(empleado.getApellidos());
        lblEdad.setText(empleado.getEdad() + "");

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
        iconoEliminar = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        iconoNombre = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        iconoApellidos = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        iconoEdad = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(Constantes.COLOR_GRIS_TRANSPARENTE);
        setMinimumSize(new java.awt.Dimension(122, 193));
        setName(""); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(120, 193));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new Color(69, 157, 140, 150));
        jPanel1.setPreferredSize(new java.awt.Dimension(120, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconoEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(iconoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 22, 20));

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 80));

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(iconoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 20));

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblNombre.setText("jLabel2");
        lblNombre.setMaximumSize(new java.awt.Dimension(60, 14));
        jPanel2.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, 20));

        lblApellidos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblApellidos.setText("jLabel2");
        lblApellidos.setMaximumSize(new java.awt.Dimension(60, 14));
        jPanel2.add(lblApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 20));

        iconoApellidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(iconoApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 20, 20));

        lblEdad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblEdad.setText("jLabel2");
        lblEdad.setMaximumSize(new java.awt.Dimension(60, 14));
        jPanel2.add(lblEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, 20));

        iconoEdad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(iconoEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 20, 20));

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, -1));

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void iconoEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoEliminarMouseClicked
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Confirma que quiere liminar este empleado?", "Confirmar", JOptionPane.WARNING_MESSAGE);
        if (confirmacion == JOptionPane.YES_OPTION) {
            ControladorFormPersonal.eliminarEmpleado(empleado);
        }
    }//GEN-LAST:event_iconoEliminarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ReporteEmpleado repEmpleado = new ReporteEmpleado();
            repEmpleado.generarReporte(String.valueOf(empleado.getIdEmpleado()));
        } catch (JRException ex) {
            Logger.getLogger(EmpleadoItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(30, 30);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconoApellidos;
    private javax.swing.JLabel iconoEdad;
    private javax.swing.JLabel iconoEliminar;
    private javax.swing.JLabel iconoNombre;
    private javax.swing.JLabel imagen;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}
