/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author raul_correa
 */
public class Ventas {
    String numeroTicket;
    String Cliente;
    float total;
    int cantidad;
    String fecha;
    Ventas sig;

    public Ventas(String numeroTicket, String Cliente, float total, int cantidad, String fecha) {
        this.numeroTicket = numeroTicket;
        this.Cliente = Cliente;
        this.total = total;
        this.cantidad = cantidad;
        this.fecha = fecha;
        sig=null;
    }

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
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

    public Ventas getSig() {
        return sig;
    }

    public void setSig(Ventas sig) {
        this.sig = sig;
    }
    
}
