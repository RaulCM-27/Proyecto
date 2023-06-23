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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaAdminController implements Initializable {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btCatalogo;

    @FXML
    private Pane panePrincipal;

    private Node vistaCliente;
    private Node vistaCatalogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader clienteLoader = new FXMLLoader(getClass().getResource("/vista/vistaCliente.fxml"));
            vistaCliente = clienteLoader.load();

            FXMLLoader catalogoLoader = new FXMLLoader(getClass().getResource("/vista/vistaCatalogo.fxml"));
            vistaCatalogo = catalogoLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cargarVistaCliente(MouseEvent event) throws IOException {

        panePrincipal.getChildren().setAll(vistaCliente);
    }

    @FXML
    private void cargarVistaCatalogo(MouseEvent event) throws IOException {
      
        panePrincipal.getChildren().setAll(vistaCatalogo);
    }

}
