
package modelo;

/**
 *
 * @author raul_correa
 */
public class Vender {
    int codigo;
    String marca;
    String modelo;
    int cantidad;
    float precio;
    int stock;
    Vender sig;

    public Vender(int codigo, String marca, String modelo, int cantidad, float precio, int stock) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.stock = stock;
        sig = null;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Vender getSig() {
        return sig;
    }

    public void setSig(Vender sig) {
        this.sig = sig;
    }
  
}
