
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaRegistroController implements Initializable {

    @FXML
    private PasswordField txtCContraseña;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private ComboBox<?> cbTipoUsuario;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eventKey(KeyEvent event) {
        String c = event.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            event.consume();
        }
    }
    
}
