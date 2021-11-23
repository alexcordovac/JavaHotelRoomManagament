/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import alertas.SolicitarDBProps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class Conexion {

    public static Connection con;
    public static ConexionProps propiedades;
    private String dirActual = System.getProperty("user.dir");
    private String nombreArchivo = "dbprops.dat";
    SolicitarDBProps soli = new SolicitarDBProps(null, true);

    public Conexion() {
    }

    public static Connection conectar() {
        con = null;

        String url = "jdbc:mysql://localhost:" + propiedades.getPuerto() + "/" + propiedades.getDb()
                + "?useSSL=false&serverTimezone=UTC&user=" + propiedades.getUsuario() + "&password=" + propiedades.getContrasena();
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            System.err.print(e.getMessage());
        }
        return con;
    }

    public static boolean desconectar() {
        if (con != null) {
            try {
                con.close();
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion" + ex.getMessage());
            }
        }
        return false;
    }

    public void serializarProps() {
        try {
            //Guardar objeto en un archivo
            FileOutputStream file = new FileOutputStream(dirActual + "\\" + nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Serialización
            out.writeObject(propiedades);

            out.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("IOException en serializarProps: "+ex.getMessage());
        }

    }

    public ConexionProps deserealizarProps() {
        ConexionProps conPropsTmp = null;
        try {
            //Leer el archivo
            File archivo = new File(dirActual + "\\" + nombreArchivo);
            archivo.createNewFile();

            FileInputStream file = new FileInputStream(archivo);
            //Si el archivo no tiene nada
            if (file.available() == 0) {
                return null;
            }
            ObjectInputStream in = new ObjectInputStream(file);

            // Deserializacion del objeto
            conPropsTmp = (ConexionProps) in.readObject();

            in.close();
            file.close();

        } catch (IOException ex) {
            System.out.println("IOException is caughten deserealizarProps: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException en deserealizarProps: "+ex.getMessage());
        }
        return conPropsTmp;
    }

    public void inicializarProps() {
        ConexionProps props = deserealizarProps();
        if(props != null){
            propiedades = props;
            conectar();
            //Si la conexion sigue siendo null
            if(con==null){
                soli.getPropieades();
                soli.setVisible(true);
            }else{
                desconectar();
            }
        }else{
            soli.setVisible(true);
        }
    }
}
