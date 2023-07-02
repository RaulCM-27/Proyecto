/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaVenderController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtPago;
    @FXML
    private TextField txtCambio;
    @FXML
    private TextField txtTotal;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();
    @FXML
    private Button btnComprar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setAddVender(ActionEvent event) {
        

    }

    @FXML
    private void buscarProducto(ActionEvent event) {
        int codigo = Integer.parseInt(txtCodigo.getText());

        String consulta = "SELECT * FROM productos WHERE codigo = ?";
        try ( Connection conn = con.ConectarseBD();  
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtCodigo.setText(rs.getInt("codigo") + "");
                txtMarca.setText(rs.getString("marca"));
                txtModelo.setText(rs.getString("modelo"));
                txtPrecio.setText(rs.getFloat("precioVenta") + "");
                txtStock.setText(rs.getInt("cantidad") + "");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Producto no encontrado");
                alert.setContentText("No se encontró ningún producto con el ID especificado.");
                alert.showAndWait();
            }

            rs.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("El Codigo debe ser un número entero.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void generarCompra(ActionEvent event) {
    }
    
}
