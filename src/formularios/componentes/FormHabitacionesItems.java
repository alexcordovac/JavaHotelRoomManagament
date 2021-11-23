/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.componentes;

import dao.DAOHabitacion;
import dao.DAOReservacion;
import formularios.FormCrearReservacion;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import jiconfont.swing.IconFontSwing;
import modelos.Habitacion;
import modelos.Reservacion;
import recursos.iconos.GoogleMaterialDesignIcons;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class FormHabitacionesItems extends javax.swing.JPanel {

    Habitacion habitacion;
    DAOHabitacion daoHabitacion;
    DAOReservacion daoReservacion;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private Timer timer;

    /**
     * Creates new form FormHabitacionesItems
     *
     * @param habitacion
     */
    public FormHabitacionesItems(Habitacion habitacion) {
        initComponents();
        pintarIconos();
        this.habitacion = habitacion;
        cargarHabitacionInfo();

        //Si la habitación está ocupada comenzamos el temporizador
        if (habitacion.getEstatus().equalsIgnoreCase("Ocupado")) {
            setupHabitacionOcupada();
        }
        
    }
    
    private void pintarIconos(){
        btnReservar.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DATE_RANGE, 25, new Color(64, 63, 62)));
        btnReservar.setPressedIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DATE_RANGE, 28, new Color(64, 63, 62)));
        lblRelojIcono1.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SCHEDULE, 20, new Color(64, 63, 62)));
        lblRelojIcono2.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FLAG, 20, new Color(64, 63, 62)));
    }

    private void setupHabitacionOcupada() {
        daoReservacion = new DAOReservacion();
        Reservacion reservacionTmp = daoReservacion.obtenerReservacionporIdHabitacion(habitacion.getId_habitacion());

        //Si aparece ocupada, pero no encuentra reservación(null), cambiamos su estado a disponible
        if (reservacionTmp == null) {
            setupHabitacionDisponible();
            return;
        }

        //inicializar contador
        fecha_entrada = reservacionTmp.getFecha_entrada();
        fecha_salida = reservacionTmp.getFecha_salida();
        iniContador();

        //Total horas
        Duration duration = Duration.between(fecha_entrada, fecha_salida);
        lblTotalHoras.setText(String.format("%02dh", duration.toHours()));
    }

    private void setupHabitacionDisponible() {
        lblContador1.setText("00h 00m 00s");

        //actualizar habitación
        daoHabitacion = new DAOHabitacion();
        daoHabitacion.actualizarEstatus(habitacion.getId_habitacion(), "Disponible");

        //vista
        habitacion.setEstatus("Disponible");
        lblEstatus.setText("Disponible");
        lblEstatus.setForeground(new Color(46, 163, 54));
        btnReservar.setEnabled(true);
    }

    public void cargarHabitacionInfo() {
        //Id
        lblIdHabitacion.setText("Id: " + String.valueOf(habitacion.getId_habitacion()));

        //Numero
        lblNoHabitacion.setText("No. " + String.valueOf(habitacion.getNo_habitacion()));

        //tipo
        lblTipo.setText("Habitación " + habitacion.getTipo_habitacion());

        //costo
        lblCosto.setText("$" + String.valueOf(habitacion.getCosto()));

        //estatus
        lblEstatus.setText(habitacion.getEstatus());
        lblEstatus.setForeground(habitacion.getEstatus().equalsIgnoreCase("disponible") ? new Color(46, 163, 54) : new Color(247, 170, 59));
        btnReservar.setEnabled(habitacion.getEstatus().equalsIgnoreCase("disponible"));
    }

    public void iniContador() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime ahora = LocalDateTime.now();
                Duration duration = Duration.between(ahora, fecha_salida);
                lblContador1.setText(format(duration));
                if (duration.isNegative()) {
                    timer.stop();
                    setupHabitacionDisponible();
                }
            }
        });
        timer.start();
    }

    protected String format(Duration duration) {
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        long seconds = duration.getSeconds() % 60;

        return String.format("%02dh %02dm %02ds", hours, mins, seconds);
    }

    /*Getters y setters*/
    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImagen = new javax.swing.JLabel();
        lblNoHabitacion = new javax.swing.JLabel();
        lblEstatus = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        btnReservar = new javax.swing.JToggleButton();
        lblRelojIcono2 = new javax.swing.JLabel();
        lblTotalHoras = new javax.swing.JLabel();
        lblIdHabitacion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblContador1 = new javax.swing.JLabel();
        lblRelojIcono1 = new javax.swing.JLabel();

        setBackground(Constantes.COLOR_GRIS_TRANSPARENTE);
        setMaximumSize(new java.awt.Dimension(32767, 110));
        setPreferredSize(new java.awt.Dimension(360, 110));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        lblNoHabitacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblEstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblCosto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCosto.setForeground(Constantes.COLOR_PRIMARIO);
        lblCosto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTipo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        btnReservar.setBackground(new java.awt.Color(250, 250, 250));
        btnReservar.setBorder(null);
        btnReservar.setBorderPainted(false);
        btnReservar.setContentAreaFilled(false);
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        lblTotalHoras.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTotalHoras.setText("00h");

        lblIdHabitacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblContador1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblContador1.setText("00h 00m 00s");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblIdHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(lblNoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblRelojIcono1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblContador1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblRelojIcono2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblContador1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRelojIcono1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblRelojIcono2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /*Pintar imagen (de las habitaciones) una vez que la vista haya renderizado*/
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        //Imagen
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/" + habitacion.getTipo_habitacion() + ".jpg")).getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH));
        this.lblImagen.setIcon(imageIcon);
    }//GEN-LAST:event_formComponentResized

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        FormCrearReservacion.id_habitacionSeleccionada = this.habitacion.getId_habitacion();
        FormCrearReservacion formCrearReservacion = new FormCrearReservacion((JFrame) SwingUtilities.getWindowAncestor(this), true);
        formCrearReservacion.show();

    }//GEN-LAST:event_btnReservarActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
    }//GEN-LAST:event_formMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnReservar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblContador1;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblIdHabitacion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNoHabitacion;
    private javax.swing.JLabel lblRelojIcono1;
    private javax.swing.JLabel lblRelojIcono2;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTotalHoras;
    // End of variables declaration//GEN-END:variables
}
