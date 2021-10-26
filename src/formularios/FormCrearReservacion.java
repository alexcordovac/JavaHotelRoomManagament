/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import controladores.ControladorHabitaciones;
import controladores.ControladorReservaciones;
import dao.DAOHabitacion;
import dao.DAOReservacion;
import java.awt.Color;
import java.awt.Font;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jiconfont.swing.IconFontSwing;
import modelos.Reservacion;
import recursos.iconos.GoogleMaterialDesignIcons;
import utiles.Constantes;
import utiles.TextPrompt;

/**
 *
 * @author Alex
 */
public class FormCrearReservacion extends javax.swing.JDialog implements Runnable {

    DocumentListener escucharFechas;
    DAOReservacion daoReservacion;
    DAOHabitacion daoHabitacion;
    public static int id_habitacionSeleccionada = 0;
    private Thread t1;

    /**
     * Creates new form FormCrearReservacion
     */
    public FormCrearReservacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        pintarIconos();
        setupFechasListener();
        listarCombobox(jComboBox1);
    }

    private void pintarIconos() {
        lblCerrarIcono.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.CLOSE, 25, Constantes.COLOR_BLANCO));
        lblFechaHoy.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DATE_RANGE, 20, Constantes.COLOR_BLANCO));

    }

    /*Escuchar fecha de entrada y salida y validar, además calcular dias*/
    private void setupFechasListener() {
        escucharFechas = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                //Ubicamos el textfield de donde se generó el evento
                if (e.getDocument().equals(inputFechaEntrada.getDocument())) {

                    //Si no es una fecha válida
                    if (!validarFecha(inputFechaEntrada.getText())) {
                        lblFechaEntradaError.setForeground(Color.red);
                        lblFechaEntradaError.setText("Formato de fecha inválido");
                        //Si los días ya habían sido calculados anteriormente los vaciamos
                        if (!inputDias.getText().isEmpty()) {
                            inputDias.setText("0");
                        }
                    } else {
                        lblFechaEntradaError.setText("");
                        calcularDias();
                    }
                } else {
                    if (!validarFecha(inputFechaSalida.getText())) {
                        lblFechaSalidaError.setForeground(Color.red);
                        lblFechaSalidaError.setText("Formato de fecha inválido");
                        if (!inputDias.getText().isEmpty()) {
                            inputDias.setText("0");
                        }
                    } else {
                        lblFechaSalidaError.setText("");
                        calcularDias();
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getDocument().equals(inputFechaEntrada.getDocument())) {
                    if (!validarFecha(inputFechaEntrada.getText())) {
                        lblFechaEntradaError.setForeground(Color.red);
                        lblFechaEntradaError.setText("Formato de fecha inválido");
                        if (!inputDias.getText().isEmpty()) {
                            inputDias.setText("0");
                        }

                    } else {
                        lblFechaEntradaError.setText("");
                        calcularDias();
                    }
                } else {
                    if (!validarFecha(inputFechaSalida.getText())) {
                        lblFechaSalidaError.setForeground(Color.red);
                        lblFechaSalidaError.setText("Formato de fecha inválido");
                        if (!inputDias.getText().isEmpty()) {
                            inputDias.setText("0");
                        }
                    } else {
                        lblFechaSalidaError.setText("");
                        calcularDias();
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            //Calcular dias
            private void calcularDias() {
                if (inputFechaEntrada.getText().isEmpty() || inputFechaSalida.getText().isEmpty()) {
                    return;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaEntrada = LocalDate.parse(inputFechaEntrada.getText().substring(0, 10), formatter);
                    LocalDate fechaSalida = LocalDate.parse(inputFechaSalida.getText().substring(0, 10), formatter);
                    long duracion = DAYS.between(fechaEntrada, fechaSalida);
                    inputDias.setText(String.valueOf(duracion));
                } catch (Exception e) {
                    System.out.println("Error calculanado días: " + e.getMessage());
                }

            }
        };
        inputFechaEntrada.getDocument().addDocumentListener(escucharFechas);
        inputFechaSalida.getDocument().addDocumentListener(escucharFechas);
    }

    public boolean validarFecha(String fecha) {
        /* Revisar si es null */
        if (fecha.trim().equals("")) {
            return false;
        } else {
            /*
	     * Configurar el formato,
	     * Por ejemplo MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            /* Crear LocalDateTime
	     * convertir el string a fecha 
             */
            try {
                LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    //Lista todas las asignaturas en el jComboBox
    private void listarCombobox(JComboBox combo) {
        combo.removeAllItems();
        Object[] lista = ControladorHabitaciones.getListHabitacionesDisponiblesIDs().toArray();

        if (lista.length == 0) {
            lista = new Object[]{"No hay habitaciones disponibles"};
        }

        DefaultComboBoxModel mod = new DefaultComboBoxModel(lista);
        combo.setModel(mod);
        combo.setSelectedItem(String.valueOf(id_habitacionSeleccionada));
    }

    //Run
    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fecha_inicio = LocalDateTime.parse(inputFechaEntrada.getText(), formatter);
        LocalDateTime fecha_salida = LocalDateTime.parse(inputFechaSalida.getText(), formatter);
        byte dias = Byte.parseByte(inputDias.getText());
        int id_habitacion = Integer.parseInt((String) jComboBox1.getSelectedItem());

        Reservacion reservacionTmp = new Reservacion(fecha_inicio, fecha_salida, dias, id_habitacion);
        try {

            //Crear reservacion en la DB
            daoReservacion = new DAOReservacion();
            daoReservacion.crearReservacion(reservacionTmp);

            //Actualizar el estado de la habitacion en la DB
            //Si la fecha es negativa es porque ya caducó la reservacion, si no, actualizamos a ocupado
            daoHabitacion = new DAOHabitacion();
            LocalDateTime ahora = LocalDateTime.now();
            Duration duration = Duration.between(ahora, fecha_salida);
            if (!duration.isNegative() && !duration.isZero()) {
                daoHabitacion.actualizarEstatus(id_habitacion, "Ocupado");
                //Actualizamos la vista de las habitaciones
                ControladorHabitaciones.actualizarPanelHabitaciones();
            }
            JOptionPane.showMessageDialog(this, "Guardado exitoso");

            //Actualizar tabla de reservaciones
            ControladorReservaciones.actualizarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar\nVerifique su ingreso de datos", "Algo salió mal", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanelArrastrable();
        jLabel1 = new javax.swing.JLabel();
        lblCerrarIcono = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        inputFechaEntrada = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inputFechaSalida = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputDias = new javax.swing.JTextField();
        lblFechaEntradaError = new javax.swing.JLabel();
        lblFechaSalidaError = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        lblFechaHoy = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 385));

        jPanel1.setBackground(Constantes.COLOR_PRIMARIO);
        jPanel1.setPreferredSize(new java.awt.Dimension(448, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nueva reservación");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblCerrarIcono.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrarIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrarIcono.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblCerrarIcono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarIconoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(lblCerrarIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
            .addComponent(lblCerrarIcono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(Constantes.COLOR_PRIMARIO);
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 334));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputFechaEntrada.setBackground(Constantes.COLOR_PRIMARIO);
        inputFechaEntrada.setForeground(new java.awt.Color(255, 255, 255));
        inputFechaEntrada.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        inputFechaEntrada.setPreferredSize(new java.awt.Dimension(72, 20));
        jPanel2.add(inputFechaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 41, 242, -1));
        TextPrompt placeholderFechaEntrada = new TextPrompt("Ej. "+LocalDateTime.now().withNano(0).withSecond(0).toString().replace("T", " "), inputFechaEntrada);
        placeholderFechaEntrada.changeAlpha(0.75f);
        placeholderFechaEntrada.changeStyle(Font.ITALIC);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha de entrada:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 21, -1, -1));

        inputFechaSalida.setBackground(Constantes.COLOR_PRIMARIO);
        inputFechaSalida.setForeground(new java.awt.Color(255, 255, 255));
        inputFechaSalida.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        inputFechaSalida.setPreferredSize(new java.awt.Dimension(72, 20));
        jPanel2.add(inputFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 102, 242, -1));
        TextPrompt placeholderFechaSalida = new TextPrompt("Ej. "+LocalDateTime.now().withNano(0).withSecond(0).toString().replace("T", " "), inputFechaSalida);
        placeholderFechaSalida.changeAlpha(0.75f);
        placeholderFechaSalida.changeStyle(Font.ITALIC);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de salida:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 82, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Días:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 145, -1, -1));

        inputDias.setEditable(false);
        inputDias.setBackground(Constantes.COLOR_PRIMARIO);
        inputDias.setForeground(new java.awt.Color(255, 255, 255));
        inputDias.setText("0");
        inputDias.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        inputDias.setPreferredSize(new java.awt.Dimension(72, 20));
        jPanel2.add(inputDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 165, 242, -1));
        jPanel2.add(lblFechaEntradaError, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 64, 242, 12));
        jPanel2.add(lblFechaSalidaError, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 127, 242, 12));

        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 223, 242, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Id de habitaciones disponibles:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 203, -1, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 268, 242, -1));

        lblFechaHoy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFechaHoyMouseClicked(evt);
            }
        });
        jPanel2.add(lblFechaHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 30, 20));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarIconoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarIconoMouseClicked
        id_habitacionSeleccionada = 0;
        this.dispose();
    }//GEN-LAST:event_lblCerrarIconoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Validar campos
        if (inputFechaEntrada.getText().isEmpty() || !validarFecha(inputFechaEntrada.getText()) || inputFechaSalida.getText().isEmpty()
                || !validarFecha(inputFechaSalida.getText()) || Byte.parseByte(inputDias.getText()) < 0
                || String.valueOf(jComboBox1.getSelectedItem()).equalsIgnoreCase("No hay habitaciones disponibles")) {
            return;
        }

        long tiempoInicial = System.currentTimeMillis();
        
        //Guardamos usando un hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                long hiloInicio = (System.currentTimeMillis() - tiempoInicial) / 1000;
                System.out.println("Inicio del hilo reservaciones: "+hiloInicio+"s");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fecha_inicio = LocalDateTime.parse(inputFechaEntrada.getText(), formatter);
                LocalDateTime fecha_salida = LocalDateTime.parse(inputFechaSalida.getText(), formatter);
                byte dias = Byte.parseByte(inputDias.getText());
                int id_habitacion = Integer.parseInt((String) jComboBox1.getSelectedItem());

                Reservacion reservacionTmp = new Reservacion(fecha_inicio, fecha_salida, dias, id_habitacion);
                try {

                    //Crear reservacion en la DB
                    daoReservacion = new DAOReservacion();
                    daoReservacion.crearReservacion(reservacionTmp);

                    //Actualizar el estado de la habitacion en la DB
                    //Si la fecha es negativa es porque ya caducó la reservacion, si no, actualizamos a ocupado
                    daoHabitacion = new DAOHabitacion();
                    LocalDateTime ahora = LocalDateTime.now();
                    Duration duration = Duration.between(ahora, fecha_salida);
                    if (!duration.isNegative() && !duration.isZero()) {
                        daoHabitacion.actualizarEstatus(id_habitacion, "Ocupado");
                        //Actualizamos la vista de las habitaciones
                        ControladorHabitaciones.actualizarPanelHabitaciones();
                    }
                    JOptionPane.showMessageDialog(null, "Guardado exitoso");

                    //Actualizar tabla de reservaciones
                    ControladorReservaciones.actualizarTabla();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al guardar\nVerifique su ingreso de datos", "Algo salió mal", JOptionPane.ERROR_MESSAGE);
                }
                long hiloFinal = (System.currentTimeMillis() - tiempoInicial) / 1000;
                System.out.println("Fin del hilo reservaciones: "+hiloFinal+"s");
            }
        }).start();
        
        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void lblFechaHoyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFechaHoyMouseClicked
        inputFechaEntrada.setText(LocalDateTime.now().withNano(0).withSecond(0).toString().replace("T", " "));
    }//GEN-LAST:event_lblFechaHoyMouseClicked

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
            java.util.logging.Logger.getLogger(FormCrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*Crear el dialogo y mostrarlo */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormCrearReservacion dialog = new FormCrearReservacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField inputDias;
    private javax.swing.JTextField inputFechaEntrada;
    private javax.swing.JTextField inputFechaSalida;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCerrarIcono;
    private javax.swing.JLabel lblFechaEntradaError;
    private javax.swing.JLabel lblFechaHoy;
    private javax.swing.JLabel lblFechaSalidaError;
    // End of variables declaration//GEN-END:variables

}
