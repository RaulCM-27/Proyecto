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
import javafx.collections.ObservableList;
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
import modelo.Proveedor;

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
    private ComboBox<Proveedor> txtProveedor;
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
        
        initCombo();
    }

    
    public void initCombo(){
        Proveedor p = new Proveedor();
        
        ObservableList<Proveedor> obsProveedor = p.getProveedores();
        
        this.txtProveedor.setItems(obsProveedor);
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
            

            if (getBuscarCod(codigo)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Codigo Duplicado");
                alert.setContentText("El Codigo ingresado ya existe en la lista.");
                alert.showAndWait();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
                return;
            }
            
            if (existeCODEnBD(codigo)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Codigo Duplicado");
                alert.setContentText("El Codigo ingresado ya existe en la lista.");
                alert.showAndWait();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
                return;
            }

            // Agregar productos a la lista 
            Productos pro = new Productos(codigo, marca, modelo, ram, almacenamiento, precioVenta, precioCompra);
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
            String consulta = "INSERT INTO productos(id, codigo, marca, modelo, ram, almacenamiento, precioVenta, precioCompra, idproveedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement(consulta)) {
                ps.setInt(1, codigo);
                ps.setString(2, marca);
                ps.setString(3, modelo);
                ps.setString(4, ram);
                ps.setString(5, almacenamiento);
                ps.setFloat(6, precioVenta);
                ps.setFloat(7, precioCompra);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
                return;
            }
           
            limpiar();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("DATOS NO VALIDOS!!");
            alert.showAndWait();
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

    private boolean existeCODEnBD(int cod) {
        String consulta = "SELECT codigo FROM productos WHERE codigo = ?";
        try ( Connection conn = con.ConectarseBD();  
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, cod);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

}
