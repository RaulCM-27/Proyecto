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
    private Button btnVender;
    
    @FXML
    private Button btnComprar;
    
    @FXML
    private Button btnProductos;
    
    @FXML
    private Button btnVentas;
    
    @FXML
    private Button btnCompras;
    
    @FXML
    private Button btnProveedor;
    
    @FXML
    private Pane panePrincipal;
    
    private Node vistaCliente;
    private Node vistaCatalogo;
    private Node vistaVender;
    private Node vistaComprar;
    private Node vistaProductos;
    private Node vistaVentas; 
    private Node vistaCompras;
    private Node vistaProveedor;
    
    
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
            
            FXMLLoader venderLoader = new FXMLLoader(getClass().getResource("/vista/vistaVender.fxml"));
            vistaVender = venderLoader.load();
            
            FXMLLoader comprarLoader = new FXMLLoader(getClass().getResource("/vista/vistaComprar.fxml"));
            vistaComprar = comprarLoader.load();
            
            FXMLLoader productosLoader = new FXMLLoader(getClass().getResource("/vista/vistaProductos.fxml"));
            vistaProductos = productosLoader.load();
            
            FXMLLoader ventasLoader = new FXMLLoader(getClass().getResource("/vista/vistaVentas.fxml"));
            vistaVentas = ventasLoader.load();
            
            FXMLLoader comprasLoader = new FXMLLoader(getClass().getResource("/vista/vistaCompras.fxml"));
            vistaCompras = comprasLoader.load();
            
            FXMLLoader proveedorLoader = new FXMLLoader(getClass().getResource("/vista/vistaProveedor.fxml"));
            vistaProveedor = proveedorLoader.load();
            
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

    @FXML
    private void cargarVistaVender(MouseEvent event) {
        
         panePrincipal.getChildren().setAll(vistaVender);
    }

    @FXML
    private void cargarVistaComprar(MouseEvent event) {
        
        panePrincipal.getChildren().setAll(vistaComprar);
    }

    @FXML
    private void cargarVistaProductos(MouseEvent event) {
        
        panePrincipal.getChildren().setAll(vistaProductos);
    }

    @FXML
    private void cargarVistaVentas(MouseEvent event) {
        
        panePrincipal.getChildren().setAll(vistaVentas);
    }

    @FXML
    private void cargarVistaCompras(MouseEvent event) {
        
        panePrincipal.getChildren().setAll(vistaCompras);
    }

    @FXML
    private void cargarVistaProveedor(MouseEvent event) {
        
        panePrincipal.getChildren().setAll(vistaProveedor);
    }

}
