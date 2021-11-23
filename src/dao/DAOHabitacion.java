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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Habitacion;
import utiles.Conexion;

/**
 *
 * @author Alex
 */
public class DAOHabitacion {

    public boolean registrarHabitacion(Habitacion habitacion) {
        PreparedStatement stm;
        Connection con;

        String sql = "INSERT INTO habitaciones(tipo_habitacion, no_habitacion, costo) VALUES (?,?,?)";
        try {
            con = Conexion.conectar();
            stm = con.prepareStatement(sql);
            stm.setString(1, habitacion.getTipo_habitacion());
            stm.setShort(2, habitacion.getNo_habitacion());
            stm.setFloat(3, habitacion.getCosto());
            int filas = stm.executeUpdate();
            System.out.println("Habitación agregada. Filas afectadas: "+filas);
            stm.close();
            con.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Clase DAOHabitacion/registrarHabitacion");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Habitacion> obtenerHabitaciones() {
        Connection co;
        Statement stm;
        ResultSet rs;

        String sql = "SELECT * FROM habitaciones";
        List<Habitacion> listaHabitaciones = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Habitacion habitacionTmp = new Habitacion();
                habitacionTmp.setId_habitacion(rs.getInt(1));
                habitacionTmp.setTipo_habitacion(rs.getString(2));
                habitacionTmp.setNo_habitacion(rs.getShort(3));
                habitacionTmp.setEstatus(rs.getString(4));
                habitacionTmp.setCosto(rs.getFloat(5));

                listaHabitaciones.add(habitacionTmp);
            }
            stm.close();
            rs.close();
            co.close();
            Conexion.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Clase DAOHabitacion/obtenerHabitaciones");
            System.out.println(e.getMessage());
        }
        return listaHabitaciones;
    }
    
    public boolean actualizarEstatus(int id, String estado) {
        Connection con;
        Statement stm;
        
        String sql="UPDATE habitaciones SET estatus ='"+estado+"' WHERE id_habitacion="+id;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            int filas = stm.executeUpdate(sql);
            System.out.println("Se actualizó el estado de la habitacion con ID: "+id+"\nFilas afectadas: "+filas);
            Conexion.desconectar();
            con.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Clase DAOHabitacion/actualizarEstatus");
            System.out.println(e.getMessage());
        }		
        return false;
    }

}
