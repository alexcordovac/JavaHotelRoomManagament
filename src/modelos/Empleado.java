/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Alex
 */
public class Empleado {
    
    private int idEmpleado;
    private String nombre;
    private String apellidos;
    private byte edad;
    private String puesto;
    private int sueldo;
    private String area;
    
    /*CONSTRUCTORES*/
    public Empleado() {
    }

    public Empleado(String nombre, String apellido, byte edad, String puesto, int sueldo, String area) {
        this.nombre = nombre;
        this.apellidos = apellido;
        this.edad = edad;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.area = area;
    }
    
    public Empleado(int idEmpleado, String nombre, String apellido, byte edad, String puesto, int sueldo, String area) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.edad = edad;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.area = area;
    }
    
    /*GETTER Y SETTERS*/
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    /*TO STRING*/
    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellidos + ", edad=" + edad + ", puesto=" + puesto + ", sueldo=" + sueldo + ", area=" + area + '}';
    }
}
