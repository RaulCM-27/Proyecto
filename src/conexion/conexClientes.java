/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import modelo.Cliente;

/**
 *
 * @author raul_correa
 */
public class conexClientes {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;

    public boolean RegistrarCliente(Cliente cl) {
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direcion) VALUES (?,?,?,?,)";

        try {
            con = cn.ConectarseBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setInt(1, cl.getTelefono());
            ps.setString(2, cl.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.toString());
            alert.showAndWait();
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(e.toString());
                alert.showAndWait();
            }
        }
    }
}
