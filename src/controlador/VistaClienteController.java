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
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaClienteController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TableView<Cliente> tblCliente;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private MenuItem contexMenu;


    Cliente cab;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        llenarTabla("Clientes");
    }

    @FXML
    private void setAddCliente(ActionEvent event) {

        try {
            int dni = Integer.parseInt(txtDNI.getText());
            String nombre = txtNombre.getText();
            int telefono = Integer.parseInt(txtTelefono.getText());
            String direccion = txtDireccion.getText();

            if (getBuscarDNI(dni)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("DNI Duplicado");
                alert.setContentText("El DNI ingresado ya existe en la lista.");
                alert.showAndWait();
                txtDNI.setText("");
                txtDNI.requestFocus();
                return;
            }

            if (existeDNIEnBD(dni)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("DNI Duplicado");
                alert.setContentText("El DNI ingresado ya existe en la lista.");
                alert.showAndWait();
                txtDNI.setText("");
                txtDNI.requestFocus();
                return;
            }

            // Agregar el cliente a la lista
            Cliente cliente = new Cliente(dni, nombre, telefono, direccion);
            if (cab == null) {
                cab = cliente;
            } else {
                Cliente ultimo = cab;
                while (ultimo.getSig() != null) {
                    ultimo = ultimo.getSig();
                }
                ultimo.setSig(cliente);
            }
            tblCliente.getItems().add(cliente);

            // Insertar datos en la base de datos
            String consulta = "INSERT INTO clientes(dni, nombre, telefono, direccion) VALUES (?, ?, ?, ?)";
            try ( Connection conn = con.ConectarseBD();  
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
                ps.setInt(1, dni);
                ps.setString(2, nombre);
                ps.setInt(3, telefono);
                ps.setString(4, direccion);
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
            alert.setContentText("El DNI o teléfono no es un número válido");
            alert.showAndWait();
        }
    }

    void limpiar() {
        txtDNI.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    public boolean getBuscarDNI(int dni) {
        if (cab == null) {
            return false;
        } else {
            Cliente p = cab;
            while (p != null) {
                if (p.getDni() == dni) {
                    return true;
                } else {
                    p = p.getSig();
                }
            }
            return false;
        }
    }

    private boolean existeDNIEnBD(int dni) {
        String consulta = "SELECT dni FROM clientes WHERE dni = ?";
        try ( Connection conn = con.ConectarseBD();  
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(consulta)) {
            ps.setInt(1, dni);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Retorna true si encuentra un DNI igual en la base de datos
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void llenarTabla(String tabla) {
        String sql = "SELECT * FROM " + tabla;
        Conexion con = new Conexion();
        Connection cn = con.ConectarseBD();

        colID.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ObservableList<Cliente> clientes = FXCollections.observableArrayList();

            while (rs.next()) {
                int dni = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                int telefono = rs.getInt("telefono");
                String direccion = rs.getString("direccion");

                Cliente cliente = new Cliente(dni, nombre, telefono, direccion);
                clientes.add(cliente);
            }

            tblCliente.setItems(clientes);

            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void limpiarTxt(ActionEvent event) {
        txtDNI.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtDireccion.clear();
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Cliente c = this.tblCliente.getSelectionModel().getSelectedItem();

        if (c != null) {
            this.txtDNI.setText(c.getDni() + "");
            this.txtNombre.setText(c.getNombre());
            this.txtTelefono.setText(c.getTelefono() + "");
            this.txtDireccion.setText(c.getDireccion());
        }
    }

    @FXML
    private void eliminarCliente(ActionEvent event) {
        
        int dni = Integer.parseInt(txtDNI.getText());

        Cliente actual = cab;
        Cliente anterior = null;

        while (actual != null) {
            if (actual.getDni() == dni) {
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

        String sql = "DELETE FROM clientes WHERE dni = ?";
        try (
            Connection cn = con.ConectarseBD();  
            PreparedStatement statement = (PreparedStatement) cn.prepareStatement(sql)) {
            statement.setInt(1, dni);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Actualizar la TableView
        tblCliente.getItems().removeIf(cliente -> cliente.getDni() == dni);
        tblCliente.refresh();
    }
    
    
}
