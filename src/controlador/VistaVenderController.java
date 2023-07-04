/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Vender;

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
    private Button btnVender;
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
    private TableView tblVender;

    Stack<Vender> proTemporales = new Stack<>();

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    private void setAddAgregar(ActionEvent event) {
        int codigo = Integer.parseInt(txtCodigo.getText());
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        float precio = Float.parseFloat(txtPrecio.getText());
        float total = Float.parseFloat(txtTotal.getText());
        String dniCliente = txtCliente.getText();
        String nombreC = txtNombreCliente.getText();

        try {

            Vender vender = new Vender(codigo, marca, modelo, cantidad, precio, total);

            int pos = proTemporales.indexOf(vender);

            if (pos == -1) {
                proTemporales.push(vender);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Producto Agregado");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El producto ya esta agregado");
                alert.showAndWait();
            }

            tblVender.getItems().add(vender);
            tblVender.refresh();

            String consulta = "INSERT INTO vender(codigoP, marcaP, modeloP, cantidadP, totalP, dniC, nombreC) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
                ps.setInt(1, codigo);
                ps.setString(2, marca);
                ps.setString(3, modelo);
                ps.setInt(4, cantidad);
                ps.setFloat(5, total);
                ps.setString(6, dniCliente);
                ps.setString(7, nombreC);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText("Valor no válido");
            alert.setContentText("DATOS NO VÁLIDOS");
            alert.showAndWait();
        }

        String consulta = "UPDATE productos SET cantidad = cantidad - ? WHERE codigo = ? ";
        try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            txtCambio.setText(""); 
        }
    }

    @FXML
    private void vender(ActionEvent event) {
        if (!proTemporales.empty()) {
            proTemporales.pop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("VENTA REALIZADA");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("NO HAY ELEMENTOS EN LA PILA");
            alert.showAndWait();
        }

        tblVender.getItems().clear();
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txtCodigo.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtPrecio.clear();
        txtStock.clear();
        txtCantidad.clear();
        txtCliente.clear();
        txtNombreCliente.clear();
        txtTotal.clear();
        txtPago.clear();
    }

}
