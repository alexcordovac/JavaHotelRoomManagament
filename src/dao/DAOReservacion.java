/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Reservacion;
import utiles.Conexion;

/**
 *
 * @author Alex
 */
public class DAOReservacion {

    public DAOReservacion() {
    }

    public List<Reservacion> obtenerReservaciones() {
        Connection co;
        Statement stm;
        ResultSet rs;

        String sql = "SELECT * FROM reservaciones";
        List<Reservacion> listaReservaciones = new ArrayList<>();
        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                Reservacion reservacionTmp = new Reservacion();
                reservacionTmp.setId_reservacion(rs.getInt(1));
                reservacionTmp.setFecha_entrada(LocalDateTime.parse(rs.getString(2), formatter));
                reservacionTmp.setFecha_salida(LocalDateTime.parse(rs.getString(3), formatter));
                reservacionTmp.setDias(rs.getByte(4));
                reservacionTmp.setId_habitacion(rs.getInt(5));

                listaReservaciones.add(reservacionTmp);
            }
            stm.close();
            rs.close();
            co.close();
            Conexion.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Clase DAOReservacion/obtenerHabitaciones");
            System.out.println(e.getMessage());
        }
        return listaReservaciones;
    }

    public boolean crearReservacion(Reservacion reservacion) {
        PreparedStatement stm;
        Connection con;

        String sql = "INSERT INTO reservaciones(fecha_ENTRADA, fecha_salida, dias, id_habitacion) VALUES (?,?,?,?)";
        try {
            con = Conexion.conectar();
            stm = con.prepareStatement(sql);
            stm.setString(1, reservacion.getFecha_entrada().toString());
            stm.setString(2, reservacion.getFecha_salida().toString());
            stm.setByte(3, reservacion.getDias());
            stm.setInt(4, reservacion.getId_habitacion());
            int filas = stm.executeUpdate();
            System.out.println("Reservacion agregada. Filas afectadas: "+filas);
            stm.close();
            con.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Clase DAOReservacion/crearReservacion");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Reservacion obtenerReservacionporIdHabitacion(int id_habitacion) {
        Connection con;
        Statement st;
        ResultSet rs;

        try {
            con = Conexion.conectar();
            st = con.createStatement();

            String query = "SELECT * FROM reservaciones WHERE id_habitacion = " + id_habitacion
                    +" AND TIMESTAMPDIFF(SECOND, fecha_salida, NOW()) < 0 LIMIT 1;";
            rs = st.executeQuery(query);
            
            Reservacion reservacionTmp = new Reservacion();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if(rs.next()){
                reservacionTmp.setId_reservacion(rs.getInt(1));
                reservacionTmp.setFecha_entrada(LocalDateTime.parse(rs.getString(2), formatter));
                reservacionTmp.setFecha_salida(LocalDateTime.parse(rs.getString(3), formatter));
                reservacionTmp.setDias(rs.getByte(4));
                reservacionTmp.setId_habitacion(rs.getInt(5));
            }else{
                return null;
            }
            st.close();
            Conexion.desconectar();
            return reservacionTmp;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Clase DAOReservacion/obtenerReservacionporIdHabitacion");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean actualizarReservacion(int id_habitacion) {
        Connection con;
        Statement st;

        try {
            con = Conexion.conectar();
            String sql = "UPDATE reservaciones AS T1 JOIN (SELECT * FROM reservaciones "
                    + "AS T2 WHERE id_habitacion = " + id_habitacion + " AND T1.dias>0 "
                    + "AND TIMESTAMPDIFF(SECOND, fecha_salida, NOW()) >= 0 ) "
                    + "AS T3 ON T1.id_reservacion = T3.id_reservacion SET T1.dias=0;";
            st = con.createStatement();
            int filas = st.executeUpdate(sql);
            st.close();
            con.close();
            Conexion.desconectar();
            System.out.println("La reservacion ha sido actualizada. Filas afectadas: "+filas);
            return true;
        } catch (Exception e) {
        }

        return false;
    }
}
