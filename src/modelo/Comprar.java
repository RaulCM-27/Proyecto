
package modelo;

/**
 *
 * @author raul_correa
 */
public class Comprar {
    int codigo;
    String marca;
    String modelo;
    int cantidad;
    Comprar sig;

    public Comprar(int codigo, String marca, String modelo, int cantidad) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidad = cantidad;
        sig=null;
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

    public Comprar getSig() {
        return sig;
    }

    public void setSig(Comprar sig) {
        this.sig = sig;
    }
    
}
