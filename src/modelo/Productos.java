
package modelo;

/**
 *
 * @author raul_correa
 */
public class Productos {
    int id;
    int codigo;
    String marca;
    String modelo;
    String ram;
    String almacenamiento;
    float precioCompra;
    float precioVenta;
    String idproveedor;
    Productos sig;

    public Productos(int codigo, String marca, String modelo, String ram, String almacenamiento, float precioCompra, float precioVenta, String idproveedor) {  
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.idproveedor = idproveedor;
        sig=null;
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

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Productos getSig() {
        return sig;
    }

    public void setSig(Productos sig) {
        this.sig = sig;
    }

    
}
