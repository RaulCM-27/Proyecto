
package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import conexiondb.ConexionMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author raul_correa
 */
public class Proveedor {
    int nic;
    String nombre;
    String direccion;
    String telefono;
    Proveedor sig;

    public Proveedor(int nic, String nombre, String direccion, String telefono) {
        this.nic = nic;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        sig = null;
    }

    public Proveedor() {
    }

    public int getNic() {
        return nic;
    }

    public void setNic(int nic) {
        this.nic = nic;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Proveedor getSig() {
        return sig;
    }

    public void setSig(Proveedor sig) {
        this.sig = sig;
    }
   
    @Override
    public String toString(){
        return nombre;
    }
    
    public ObservableList<Proveedor> getProveedores(){
        
        ObservableList<Proveedor> obs = FXCollections.observableArrayList();
        try {
                       
            ConexionMySQL conexion = new ConexionMySQL("localhost", "login_java_mysql","root","");
            
            conexion.ejecutarConsulta("select * from proveedor");         
            
            ResultSet rs = conexion.getResultSet();
            
            while(rs.next()){
                
                int nic = rs.getInt("nic");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                
                Proveedor c = new Proveedor(nic, nombre, direccion, telefono);
                
                obs.add(c);
            }
            
            conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obs;
    }
}















