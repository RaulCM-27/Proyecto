/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import conexion.conexClientes;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

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
    private Button btnEliminar;
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
    private TextField txtDNI;

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

            // Agregar el cliente a la lista local
            Cliente cliente = new Cliente(dni, nombre, telefono, direccion);
            if (cab == null) {
                cab = cliente;
            } else {
                Cliente ultimoNodo = cab;
                while (ultimoNodo.getSig() != null) {
                    ultimoNodo = ultimoNodo.getSig();
                }
                ultimoNodo.setSig(cliente);
            }
            tblCliente.getItems().add(cliente);

            // Insertar datos en la base de datos
            String consulta = "INSERT into clientes(dni, nombre, telefono, direccion) values ('" + dni + "', '" + nombre + "', '" + telefono + "', '" + direccion + "')";
            PreparedStatement ps = (PreparedStatement) cn.prepareStatement(consulta);
            ps.executeUpdate();
            limpiar();


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("El ID o teléfono no es un número válido");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
