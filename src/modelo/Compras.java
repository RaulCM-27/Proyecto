
package modelo;

/**
 *
 * @author raul_correa
 */
public class Compras {
    String numeroTicket;
    String proveedor;
    float total;
    int cantidad;
    String fecha;
    Compras sig;

    public Compras(String numeroTicket, String proveedor, float total, int cantidad, String fecha) {
        this.numeroTicket = numeroTicket;
        this.proveedor = proveedor;
        this.total = total;
        this.cantidad = cantidad;
        this.fecha = fecha;
        sig = null;
    }
    
    

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
        
}
