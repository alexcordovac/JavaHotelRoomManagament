/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;
import conexion.Conexion;

/**
 *
 * @author Alex
 */
public class DAOEmpleado {

    public DAOEmpleado() {
    }

    public int registrarEmpleado(Empleado em) {
        Connection con;
        PreparedStatement pst;
        String query = "INSERT INTO empleado(nombre, apellidos, edad, puesto, sueldo, area) "
                + "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            con = Conexion.conectar();
            pst = con.prepareStatement(query);

            //Asignar valores
            pst.setString(1, em.getNombre());
            pst.setString(2, em.getApellidos());
            pst.setByte(3, em.getEdad());
            pst.setString(4, em.getPuesto());
            pst.setInt(5, em.getSueldo());
            pst.setString(6, em.getArea());

            //Guardar empleado en la DB
            int filas = pst.executeUpdate();
            System.out.println("Empleado " + em.getNombre() + " agregado. Filas afectadas: " + filas);

            //Cerrar conexion
            Conexion.desconectar();
            con.close();
            pst.close();
            return filas;
        } catch (SQLException e) {
            System.err.println("Error al registrar empleado");
            e.printStackTrace();
        }

        return -1;
    }

    public int eliminarEmpleado(int idEmpleado) {
        Connection con;
        Statement st;
        String query = "DELETE FROM empleado WHERE idEmpleado = " + idEmpleado + ";";
        try {
            con = Conexion.conectar();
            st = con.createStatement();
            int filas = st.executeUpdate(query);
            st.close();
            con.close();
            return filas;
        } catch (SQLException e) {
            System.err.println("Error eliminando empleado.");
            e.printStackTrace();
        }
        return -1;
    }

    public List<Empleado> obtenerListaEmpleados() {
        Connection con;
        Statement st;
        ResultSet rs;
        String query = "SELECT * FROM empleado";
        List<Empleado> lista = null;
        try {
            lista = new ArrayList<>();
            con = Conexion.conectar();
            st = con.createStatement();

            rs = st.executeQuery(query);
            while (rs.next()) {
                int idEmpleado = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellidos = rs.getString(3);
                byte edad = rs.getByte(4);
                String puesto = rs.getString(5);
                int sueldo = rs.getInt(6);
                String area = rs.getString(7);
                Empleado tmpEmpleado = new Empleado(idEmpleado, nombre, apellidos, edad, puesto, sueldo, area);
                lista.add(tmpEmpleado);
            }
            st.close();
            rs.close();
            con.close();
            Conexion.desconectar();
            return lista;
        } catch (Exception e) {
            System.err.println("Error al leer lista de empleados.");
            e.printStackTrace();
        }

        return lista;
    }

}
