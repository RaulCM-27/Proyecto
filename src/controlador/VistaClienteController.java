/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
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
    private TextField txtID;
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

    Cliente cab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    }

    @FXML
    private void setAddCliente(ActionEvent event) {

        try {

            int id = Integer.parseInt(txtID.getText());
            String nombre = txtNombre.getText();
            int telefono = Integer.parseInt(txtTelefono.getText());
            String direccion = txtDireccion.getText();

            if (getBuscarID(id)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID Duplicado");
                alert.setContentText("El ID ingresado ya existe en la lista.");
                alert.showAndWait();
                txtID.setText("");
                txtID.requestFocus();
                return;
            }

            Cliente cliente = new Cliente(id, nombre, telefono, direccion);

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

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("El ID o teléfono no es un número válido");
            alert.showAndWait();
        }
    }

    public boolean getBuscarID(int id) {
        if (cab == null) {
            return false;
        } else {
            Cliente p = cab;
            while (p != null) {
                if (p.getID() == id) {
                    return true;
                } else {
                    p = p.getSig();
                }
            }
            return false;
        }
    }
}
