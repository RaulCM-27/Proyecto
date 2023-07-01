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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Proveedor;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaProveedorController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextField txtNIC;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TableView<Proveedor> tblProveedor;
    @FXML
    private TableColumn colNIC;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private MenuItem contexMenu;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    Proveedor cab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        llenarTablaP("Proveedor");
    }

    @FXML
    private void setAddProveedor(ActionEvent event) {

        try {
            int nic = Integer.parseInt(txtNIC.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();

            if (getBuscarNIC(nic)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("NIC Duplicado");
                alert.setContentText("El NIC ingresado ya existe en la lista.");
                alert.showAndWait();
                txtNIC.setText("");
                txtNIC.requestFocus();
                return;
            }

            if (existeNICenBD(nic)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("NIC Duplicado");
                alert.setContentText("El NIC ingresado ya existe en la base de datos.");
                alert.showAndWait();
                txtNIC.setText("");
                txtNIC.requestFocus();
                return;
            }

            // Agregar el proveedor a la lista enlazada
            Proveedor proveedor = new Proveedor(nic, nombre, direccion, telefono);
            if (cab == null) {
                cab = proveedor;
            } else {
                Proveedor ultimo = cab;
                while (ultimo.getSig() != null) {
                    ultimo = ultimo.getSig();
                }
                ultimo.setSig(proveedor);
            }
            tblProveedor.getItems().add(proveedor);

            // Insertar datos en la base de datos
            String consulta = "INSERT INTO proveedor(nic, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
            try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
                ps.setInt(1, nic);
                ps.setString(2, nombre);
                ps.setString(3, direccion);
                ps.setString(4, telefono);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            limpiar();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText("Valor no válido");
            alert.setContentText("DATOS NO VÁLIDOS");
            alert.showAndWait();
        }
    }

    void limpiar() {
        txtNIC.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    public boolean getBuscarNIC(int nic) {
        if (cab == null) {
            return false;
        } else {
            Proveedor p = cab;
            while (p != null) {
                if (p.getNic() == nic) {
                    return true;
                } else {
                    p = p.getSig();
                }
            }
            return false;
        }
    }

    private boolean existeNICenBD(int nic) {
        String consulta = "SELECT nic FROM proveedor WHERE nic = ?";
        try ( Connection conn = con.ConectarseBD();  PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, nic);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void llenarTablaP(String tablaP) {
        String sql = "SELECT * FROM " + tablaP;
        Conexion con = new Conexion();
        Connection cn = con.ConectarseBD();

        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();

            while (rs.next()) {
                int nic = rs.getInt("nic");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");

                Proveedor proveedor = new Proveedor(nic, nombre, direccion, telefono);
                proveedores.add(proveedor);
            }

            tblProveedor.setItems(proveedores);

            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void limpiartxt(ActionEvent event) {
        txtNIC.clear();
        txtNombre.clear();
        txtDireccion.clear();
        txtTelefono.clear();
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Proveedor p = this.tblProveedor.getSelectionModel().getSelectedItem();

        if (p != null) {
            this.txtNIC.setText(p.getNic() + "");
            this.txtNombre.setText(p.getNombre());
            this.txtDireccion.setText(p.getDireccion());
            this.txtTelefono.setText(p.getTelefono());
        }
    }

    @FXML
    private void eliminarProveedor(ActionEvent event) {
        int nic = Integer.parseInt(txtNIC.getText());

        Proveedor actual = cab;
        Proveedor anterior = null;

        while (actual != null) {
            if (actual.getNic() == nic) {
                if (anterior == null) {
                    // El proveedor a eliminar es el primero
                    cab = actual.getSig();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ELIMINAR");
                    alert.setHeaderText(null);
                    alert.setContentText("Proveedor eliminado, al inicio de la lista");
                    alert.showAndWait();
                    break;
                } else {
                    // El proveedor a eliminar está en el medio o al final
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

        String sql = "DELETE FROM proveedor WHERE nic = ?";
        try (
                 Connection cn = con.ConectarseBD();  PreparedStatement statement = (PreparedStatement) cn.prepareStatement(sql)) {
            statement.setInt(1, nic);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Manejar la excepción de SQL
            e.printStackTrace();
            return;
        }

        // Actualizar la TableView
        tblProveedor.getItems().removeIf(proveedor -> proveedor.getNic() == nic);
        tblProveedor.refresh();
    }

}


