/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaAdminController implements Initializable {

    @FXML
    private Button btnCliente;
    @FXML
    private AnchorPane paneCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cargarVistaCliente(MouseEvent event) throws IOException {
        
        AnchorPane secondFXML = FXMLLoader.load(getClass().getResource("/vista/vistaCliente.fxml"));
        paneCliente.getChildren().setAll(secondFXML);
    }
    
}
