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
import javafx.scene.control.TableColumn;
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
    private TextField txtCliente;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtPago;
    @FXML
    private TextField txtCambio;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnImprimir;
    @FXML
    private TextField txtStock;
    @FXML
    private Button btnCalcular;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TableColumn colTotal;
    @FXML
    private TableColumn colTotalPagar;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

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
        try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
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
    private void buscarCliente(ActionEvent event) {
        int dni = Integer.parseInt(txtCliente.getText());

        String consulta = "SELECT * FROM clientes WHERE dni = ?";
        try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtCliente.setText(rs.getInt("dni") + "");
                txtNombreCliente.setText(rs.getString("nombre"));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se encontró ningún Cliente con el DNI especificado.");
                alert.showAndWait();
            }

            rs.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("El DNI debe ser un número entero.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void calcularPago(ActionEvent event) {

        try {
            int cant = Integer.parseInt(txtCantidad.getText());
            float precio = Float.parseFloat(txtPrecio.getText());

            float totalPagar = cant * precio;

            txtTotal.setText(String.valueOf(totalPagar));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Valores no válidos");
            alert.showAndWait();
            txtTotal.setText(""); 
        }
    }

    @FXML
    private void calcularCambio(ActionEvent event) {
        try {
            float pago = Float.parseFloat(txtPago.getText());
            float totalPagar = Float.parseFloat(txtTotal.getText());

            if (pago >= totalPagar) {
                float cambio = pago - totalPagar;
                txtCambio.setText(String.valueOf(cambio));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El pago no es suficiente");
                alert.showAndWait();
                txtCambio.setText("");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Pago o total no válidos");
            alert.showAndWait();
            txtCambio.setText(""); // Limpia el campo de texto de cambio
        }
    }

}
