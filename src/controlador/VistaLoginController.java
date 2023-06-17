/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaLoginController implements Initializable {
    
   
        
    @FXML
    private Button btnIniciar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Button btnRegistrarse;

     @FXML
    private void eventKey(KeyEvent event) {
        String c = event.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            event.consume();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        
        Conexion con = new Conexion();
        Connection cn = cn=(Connection) con.ConectarseBD();

    }
    
}












