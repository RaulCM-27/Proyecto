
package controlador;

import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;
import conexion.Conexion;


/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaRegistroController implements Initializable {
    
    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    @FXML
    private PasswordField txtCContraseña;
    
    @FXML
    private Button btnRegistrarse;
    
    @FXML
    private ComboBox<String> cbTipoUsuario;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtCUsuario;
    
    @FXML
    private Button btnSalir;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, new String[]{"Admin", "vendedor"});
        
        cbTipoUsuario.getItems().addAll(list);
    }    

    @FXML
    private void eventKey(KeyEvent event) {
        String c = event.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            event.consume();
        }
    }

    @FXML
    private void guardarUsuario(ActionEvent event) {
        
        String nombre = this.txtNombre.getText();
        String usuario = this.txtCUsuario.getText();
        String contraseña = this.txtCContraseña.getText();
        String tipoUsuario = this.cbTipoUsuario.getSelectionModel().getSelectedItem();
        
        //validacion
        if(nombre.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()){
            JOptionPane.showMessageDialog(null, "POR FAVOR COMPLETAR DATOS!!");
        }else{
            if(tipoUsuario.equalsIgnoreCase(null)){
                JOptionPane.showMessageDialog(null, "Ingresar Tipo de Usuario");
            }else{
                try {
                    String consulta = "INSERT into usuario(nombre, usuario, contraseña, tipoUsuario)values('"+nombre+"','"+usuario+"','"+contraseña+"','"+tipoUsuario+"')";
                    PreparedStatement ps =(PreparedStatement) cn.prepareStatement(consulta); 
                    ps.executeUpdate();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "DATOS REGISTRADOR CORRECTAMENTE");
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR USUARIO!!"+e);
                }
            }
        }
      
        
    }

    @FXML
    private void salir(ActionEvent event) {
    }

    
    
    @FXML
    private void comboboxEvent(ActionEvent event) {
    }
    
    void limpiar(){
        txtNombre.setText("");
        txtCUsuario.setText("");
        txtCContraseña.setText("");
    }
}
