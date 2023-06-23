
package modelo;

/**
 *
 * @author raul_correa
 */
public class Productos {
    int codigo;
    String marca;
    String modelo;
    String procesador;
    String ram;
    String almacenamiento;
    float precioCompra;
    float precioVenta;
    String proveedor;
    Productos sig;

    public Productos(int codigo, String marca, String modelo, String procesador, String ram, String almacenamiento, float precioCompra, float precioVenta, String proveedor) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.procesador = procesador;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.proveedor = proveedor;
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

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Productos getSig() {
        return sig;
    }

    public void setSig(Productos sig) {
        this.sig = sig;
    }
    
}
