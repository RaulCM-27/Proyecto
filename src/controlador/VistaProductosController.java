/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import conexion.Conexion;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TextField txtCantidad;
    @FXML
    private ComboBox<Proveedor> txtProveedor;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<Productos> tblProductos;
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
    @FXML
    private TextField txtPrecio;
    @FXML
    private MenuItem contextMenu;

    Productos cab;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        colRam.setCellValueFactory(new PropertyValueFactory<>("ram"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colAlmacenamiento.setCellValueFactory(new PropertyValueFactory<>("almacenamiento"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

        initCombo();
        
        llenarTablaPS("Productos");
    }

    public void initCombo() {
        Proveedor p = new Proveedor();
        ObservableList<Proveedor> obsProveedor = p.getProveedores();
        this.txtProveedor.setItems(obsProveedor);
    }

    @FXML
    private void setAddAgregar(ActionEvent event) {
        try {
            int cod = Integer.parseInt(txtCodigo.getText());
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String ram = txtRam.getText();
            String almacenamiento = txtAlmacenamiento.getText();
            float precioVenta = Float.parseFloat(txtPrecio.getText());
            String proveedor = txtProveedor.getSelectionModel().getSelectedItem().toString();

            if (getBuscarCod(cod)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Codigo Duplicado");
                alert.setContentText("El Codigo ingresado ya existe en la lista.");
                alert.showAndWait();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
                return;
            }

            if (existeCODEnBD(cod)) {
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
            Productos pro = new Productos(cod, marca, modelo, ram, almacenamiento, precioVenta, proveedor);
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
            String consulta = "INSERT INTO productos(codigo, marca, modelo, ram, almacenamiento, precioVenta, idproveedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try ( Connection conn = con.ConectarseBD();  
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
                ps.setInt(1, cod);
                ps.setString(2, marca);
                ps.setString(3, modelo);
                ps.setString(4, ram);
                ps.setString(5, almacenamiento);
                ps.setFloat(6, precioVenta);
                ps.setString(7, proveedor);
                ps.executeUpdate();
            } catch (SQLException e) {
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
        txtCantidad.setText("");
        txtAlmacenamiento.setText("");
        txtPrecio.setText("");
        txtProveedor.getSelectionModel().clearSelection();
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
        try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, cod);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void llenarTablaPS(String tabla) {
        String sql = "SELECT * FROM " + tabla;
        Conexion con = new Conexion();
        Connection cn = con.ConectarseBD();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colRam.setCellValueFactory(new PropertyValueFactory<>("ram"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colAlmacenamiento.setCellValueFactory(new PropertyValueFactory<>("almacenamiento"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ObservableList<Productos> productos = FXCollections.observableArrayList();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String ram = rs.getString("ram");
                int cantidad = rs.getInt("cantidad");
                String almacenamiento = rs.getString("almacenamiento");
                float precio = rs.getFloat("precioVenta");

                Productos producto = new Productos(codigo, marca, modelo, ram, cantidad, almacenamiento, precio);
                productos.add(producto);
            }

            tblProductos.setItems(productos);

            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void limpiartxt(ActionEvent event) {
        txtCodigo.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtRam.setText("");
        txtCantidad.setText("");
        txtAlmacenamiento.setText("");
        txtPrecio.setText("");
        txtProveedor.getSelectionModel().clearSelection();
    }

    @FXML
    private void eliminarProducto(ActionEvent event) {

        int cod = Integer.parseInt(txtCodigo.getText());

        Productos actual = cab;
        Productos anterior = null;

        while (actual != null) {
            if (actual.getCodigo() == cod) {
                if (anterior == null) {
                    cab = actual.getSig();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ELIMINAR");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente eliminado, al inicio de la lista");
                    alert.showAndWait();
                    break;
                } else {
                    anterior.setSig(actual.getSig());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ELIMINAR");
                    alert.setHeaderText(null);
                    alert.setContentText("Proveedor eliminado");
                    alert.showAndWait();
                    break;
                }
            }
            anterior = actual;
            actual = actual.getSig();
        }

        String sql = "DELETE FROM productos WHERE codigo = ?";
        try (
                 Connection cn = con.ConectarseBD();  PreparedStatement statement = (PreparedStatement) cn.prepareStatement(sql)) {
            statement.setInt(1, cod);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Actualizar la TableView
        tblProductos.getItems().removeIf(producto -> producto.getCodigo() == cod);
        tblProductos.refresh();
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Productos p = this.tblProductos.getSelectionModel().getSelectedItem();

        if (p != null) {
            txtCodigo.setText(p.getCodigo() + "");
            txtMarca.setText(p.getMarca());
            txtModelo.setText(p.getModelo());
            txtRam.setText(p.getRam());
            txtAlmacenamiento.setText(p.getAlmacenamiento());
            txtCantidad.setText(p.getCantidad() + "");
            txtPrecio.setText(p.getPrecioVenta() + "");
        }
    }

}
