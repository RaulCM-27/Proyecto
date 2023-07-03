
package modelo;

/**
 *
 * @author raul_correa
 */
public class Vender {
    int id;
    int codigo;
    String marca;
    String modelo;
    int cantidad;
    float precio; 
    float total;
    float pago;

    public Vender(int codigo, String marca, String modelo, int cantidad, float precio, float total) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Vender(int id, int codigo, String marca, String modelo, int cantidad, float precio, float total, float pago) {
        this.id = id;
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.pago = pago;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }
    
    
    
}















