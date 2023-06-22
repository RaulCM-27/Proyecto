/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ObservableList<Cliente> clientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clientes = FXCollections.observableArrayList();

        this.colID.setCellFactory(new PropertyValueFactory("ID"));
        this.colNombre.setCellFactory(new PropertyValueFactory("Nombre"));
        this.colTelefono.setCellFactory(new PropertyValueFactory("Telefono"));
        this.colDireccion.setCellFactory(new PropertyValueFactory("Direccion"));
    }

    @FXML
    private void agregarCliente(ActionEvent event) {
        
            int id = Integer.parseInt(this.txtID.getText());
            String nombre = this.txtNombre.getText();
            int telefono = Integer.parseInt(this.txtTelefono.getText());
            String direccion = this.txtDireccion.getText();

            Cliente c = new Cliente(id, nombre, telefono, direccion);

            if (!this.clientes.contains(c)) {
                this.clientes.add(c);
                this.tblCliente.setItems(clientes);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("CLIENTE EXISTENTE");
                alert.showAndWait();
            }
     
    }

}
