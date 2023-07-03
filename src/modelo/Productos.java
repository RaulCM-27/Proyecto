
package modelo;

/**
 *
 * @author raul_correa
 */
public class Productos {
    int codigo;
    String marca;
    String modelo;
    String ram;
    int cantidad;
    String almacenamiento;
    float precioVenta;
    String proveedor;
    Productos sig;

    public Productos(int codigo, String marca, String modelo, String ram, int cantidad, String almacenamiento, float precioVenta, String proveedor) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.cantidad = cantidad;
        this.almacenamiento = almacenamiento;
        this.precioVenta = precioVenta;
        this.proveedor = proveedor;
        sig=null;
    }

    public Productos(int codigo, String marca, String modelo, String ram, String almacenamiento, float precioVenta, String proveedor) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precioVenta = precioVenta;
        this.proveedor = proveedor;
    }

    

    
    
    

    public Productos(int codigo, String marca, String modelo, String ram, int cantidad, String almacenamiento, float precioVenta) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.cantidad = cantidad;
        this.almacenamiento = almacenamiento;
        this.precioVenta = precioVenta;
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

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
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
