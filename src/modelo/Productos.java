
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
    String almacenamiento;
    float precioVenta;
    float precioCompra;
    String proveedor;
    int cant;
    Productos sig;

    public Productos(int codigo, String marca, String modelo, String ram, String almacenamiento, float precioVenta, float precioCompra, String proveedor, int cant) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.proveedor = proveedor;
        this.cant = cant;
        sig=null;
    }

    public Productos(int codigo, String marca, String modelo, String ram, String almacenamiento, float precioVenta, float precioCompra, String proveedor) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.proveedor = proveedor;
    }

    public Productos(int codigo, String marca, String modelo, String ram, String almacenamiento, float precioVenta, int cant) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precioVenta = precioVenta;
        this.cant = cant;
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

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Productos getSig() {
        return sig;
    }

    public void setSig(Productos sig) {
        this.sig = sig;
    }

    
}
