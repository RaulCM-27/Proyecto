/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import conexion.Conexion;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Productos;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaProductosController implements Initializable {

    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtRam;
    @FXML
    private TextField txtAlmacenamiento;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtPrecioCompra;
    @FXML
    private TextField txtPrecioVenta;
    @FXML
    private ComboBox<String> txtProveedor;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<Productos> tblProductos;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colRam;
    @FXML
    private TableColumn colAlmacenamiento;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colPrecioVenta;

    Productos cab;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("Código"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        colRam.setCellValueFactory(new PropertyValueFactory<>("Ram"));
        colAlmacenamiento.setCellValueFactory(new PropertyValueFactory<>("Almacenamiento"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("Precio Venta"));
    }

    @FXML
    private void setAddAgregar(ActionEvent event) {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String ram = txtRam.getText();
            String almacenamiento = txtAlmacenamiento.getText();
            float precioVenta = Float.parseFloat(txtPrecioVenta.getText());
            float precioCompra = Float.parseFloat(txtPrecioCompra.getText());
            String proveedor = txtProveedor.getSelectionModel().getSelectedItem();

            if (getBuscarCod(codigo)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("DNI Duplicado");
                alert.setContentText("El Codigo ingresado ya existe en la lista.");
                alert.showAndWait();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
                return;
            }

            // Agregar productos a la lista 
            Productos pro = new Productos(codigo, marca, modelo, ram, almacenamiento, precioVenta, precioCompra, proveedor);
            if (cab == null) {
                cab = pro;
            } else {
                Productos ultimo = cab;
                while (ultimo.getSig() != null) {
                    ultimo = ultimo.getSig();
                }
                ultimo.setSig(pro);
            }
            tblProductos.getItems().add(pro);

            // Insertar datos en la base de datos
            String consulta = "INSERT into Productos(codigo, marca, modelo, ram, almacenamiento, proveedor, precioCompra, precioVenta) values "
                    + "('" + codigo + "', '" + marca + "', '" + modelo + "', '" + ram + "', '" + proveedor + "', '" + precioCompra + "', '" + precioVenta + "' )";
            PreparedStatement ps = (PreparedStatement) cn.prepareStatement(consulta);
            ps.executeUpdate();
            limpiar();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("DATOS NO VALIDOS!!");
            alert.showAndWait();

        } catch (SQLException e) {

        }
    }

    void limpiar() {
        txtCodigo.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtRam.setText("");
        txtAlmacenamiento.setText("");
        txtProveedor.getSelectionModel().clearSelection();
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
    }

    public boolean getBuscarCod(int cod) {
        if (cab == null) {
            return false;
        } else {
            Productos p = cab;
            while (p != null) {
                if (p.getCodigo() == cod) {
                    return true;
                } else {
                    p = p.getSig();
                }
            }
            return false;
        }
    }

    public void llenarProveedor() {

        String url = "jdbc:mysql://localhost:3306/login_java_mysql";
        String usuario = "";
        String contraseña = "";

        try ( Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT nombre FROM proveedores")) {

            while (resultSet.next()) {
                String nombreProveedor = resultSet.getString("nombre");
                txtProveedor.getItems().add(nombreProveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
