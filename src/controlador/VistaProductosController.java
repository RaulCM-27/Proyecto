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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private TextField txtProveedor;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setAddAgregar(ActionEvent event) {
    }
    
}
