
package modelo;

/**
 *
 * @author raul_correa
 */
public class Proveedor {
    int NIC;
    String nombre;
    String direccion;
    String telefono;
    Proveedor sig;

    public Proveedor(int NIC, String nombre, String direccion, String telefono) {
        this.NIC = NIC;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        sig = null;
    }

    public int getNIC() {
        return NIC;
    }

    public void setNIC(int NIC) {
        this.NIC = NIC;
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
   
}
