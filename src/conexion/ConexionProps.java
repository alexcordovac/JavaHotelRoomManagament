/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class ConexionProps implements Serializable{
    private String usuario;
    private String contrasena;
    private String puerto;
    private String db;

    public ConexionProps() {
    }

    public ConexionProps(String usuario, String contrasena, String puerto, String db) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.puerto = puerto;
        this.db = db;
    }
    
    /*GETTER Y SETTERS*/
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
