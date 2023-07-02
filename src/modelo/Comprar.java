/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author raul_correa
 */
public class Comprar {
    int id;
    int codProducto;
    String marcaP;
    String modeloP;
    int cantidad;
    float total;

    public Comprar(int id, int codProducto, String marcaP, String modeloP, int cantidad, float total) {
        this.id = id;
        this.codProducto = codProducto;
        this.marcaP = marcaP;
        this.modeloP = modeloP;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Comprar(int codProducto, String marcaP, String modeloP, int cantidad, float total) {
        this.codProducto = codProducto;
        this.marcaP = marcaP;
        this.modeloP = modeloP;
        this.cantidad = cantidad;
        this.total = total;
    }

    
    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarcaP() {
        return marcaP;
    }

    public void setMarcaP(String marcaP) {
        this.marcaP = marcaP;
    }

    public String getModeloP() {
        return modeloP;
    }

    public void setModeloP(String modeloP) {
        this.modeloP = modeloP;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
