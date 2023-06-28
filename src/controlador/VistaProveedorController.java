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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button btnEliminar;
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

    Proveedor cab;

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    }

    @FXML
    private void setAddProveedor(ActionEvent event) {

        try{
            int nic = Integer.parseInt(txtNIC.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();

            if (getBuscarNIC(nic)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("NIC Duplicado");
                alert.setContentText("El DNI ingresado ya existe en la lista.");
                alert.showAndWait();
                txtNIC.setText("");
                txtNIC.requestFocus();
                return;
            }

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
            
            String consulta = "INSERT INTO proveedor(nic, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = (PreparedStatement) cn.prepareStatement(consulta);
            ps.setInt(1, nic);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setString(4, telefono);
            ps.executeUpdate();
            ps.close();
            cn.close();

            limpiar();
            
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("El ID o teléfono no es un número válido");
            alert.showAndWait();
        }catch (SQLException e) {
            e.printStackTrace();
        }
            
    }

    public boolean getBuscarNIC(int nic) {
        if (cab == null) {
            return false;
        } else {
            Proveedor p = cab;
            while (p != null) {
                if (p.getNIC() == nic) {
                    return true;
                } else {
                    p = p.getSig();
                }
            }
            return false;
        }
    }

    void limpiar() {
        txtNIC.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

}
