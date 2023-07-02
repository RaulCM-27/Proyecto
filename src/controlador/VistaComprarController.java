/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import java.awt.Font;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javax.swing.text.Document;
import modelo.Productos;
import modelo.Comprar;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaComprarController implements Initializable {

    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnImprimir;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtProveedor;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TableColumn colTotal;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colProveedor;
    @FXML
    private TextField txtTotalPagar;
    @FXML
    private Button btnCalcular;
    @FXML
    private TableView tblComprar;
    @FXML
    private Button btnComprar;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    Stack<Comprar> productosTemporales = new Stack<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codProducto"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marcaP"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modeloP"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colProveedor.setCellFactory(new PropertyValueFactory<>("proveedor"));
    }

    @FXML
    private void setAddVender(ActionEvent event) {

        int codP = Integer.parseInt(txtCodigo.getText());
        String marcaP = txtMarca.getText();
        String modeloP = txtModelo.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        float total = Float.parseFloat(txtTotalPagar.getText());

        Comprar compra = new Comprar(codP, marcaP, modeloP, cantidad, total);

        int pos = productosTemporales.indexOf(compra);

        if (pos == -1) {
            productosTemporales.push(compra);  //Se invoca al método pusch que ya tiene Stack
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

        tblComprar.getItems().add(compra);

        String consulta = "INSERT INTO comprar(codigoP, marcaP, modeloP, cantidad, total) VALUES (?, ?, ?, ?, ?)";
        try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, codP);
            ps.setString(2, marcaP);
            ps.setString(3, modeloP);
            ps.setInt(4, cantidad);
            ps.setFloat(5, total);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtCodigo.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtCantidad.clear();
        txtPrecio.clear();
        txtTotalPagar.clear();
    }

    @FXML
    private void setAddLimpiar(ActionEvent event) {
        txtCodigo.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtCantidad.clear();
        txtPrecio.clear();
        txtTotalPagar.clear();
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
    private void calcularTotal(ActionEvent event) {
        int cant = Integer.parseInt(txtCantidad.getText());
        float precio = Float.parseFloat(txtPrecio.getText());

        float totalPagar = cant * precio;

        txtTotalPagar.setText(String.valueOf(totalPagar));
    }

    @FXML
    private void generarCompra(ActionEvent event) {
        if (!productosTemporales.empty()) {
            productosTemporales.pop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("COMPRA REALIZADA");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("NO HAY ELEMENTOS EN LA PILA");
            alert.showAndWait();
        }

        tblComprar.getItems().clear();
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Comprar p = (Comprar) this.tblComprar.getSelectionModel().getSelectedItem();

        if (p != null) {
            txtCodigo.setText(p.getCodProducto() + "");
            txtMarca.setText(p.getMarcaP());
            txtModelo.setText(p.getModeloP());
            txtCantidad.setText(p.getCantidad() + "");
            txtTotalPagar.setText(p.getTotal() + "");
        }
    }

}
