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
import com.mysql.jdbc.Connection;
import conexion.Conexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //metodo para el combobox
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, new String[]{"admin", "vendedor"});

        cbTipoUsuario.getItems().addAll(list);
    }

    @FXML
    private void eventKey(KeyEvent event) {
        //evitar espacios al escribir
        String c = event.getCharacter();

        if (c.equalsIgnoreCase(" ")) {
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
        if (nombre.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Por favor completar datos");
            alert.showAndWait();
        } else {
            if (tipoUsuario == null || tipoUsuario.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Ingresar Tipo de Usuario");
                alert.showAndWait();
            } else {
                try {
                    String consulta = "INSERT into usuario(nombre, usuario, contraseña, tipoUsuario)values('" + nombre + "','" + usuario + "','" + contraseña + "','" + tipoUsuario + "')";
                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement(consulta);
                    ps.executeUpdate();
                    limpiar();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Datos registrados correctamente");
                    alert.showAndWait();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error al registrar usuario");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        }             
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaLogin.fxml"));

        Parent root = loader.load();

        VistaLoginController controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        Stage myStage = (Stage) this.btnRegistrarse.getScene().getWindow();
        myStage.close();
        }catch(Exception e){
            
        }
    }

    private void salir(ActionEvent event) {
        Button closeButton = (Button) event.getSource();
        Stage stage = (Stage) this.btnRegistrarse.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void comboboxEvent(ActionEvent event) {
    }

    void limpiar() {
        txtNombre.setText("");
        txtCUsuario.setText("");
        txtCContraseña.setText("");
        cbTipoUsuario.getSelectionModel().clearSelection();
    }
    
    
    public void closeWindows() {
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaLogin.fxml"));

        Parent root = loader.load();

        VistaLoginController controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        Stage myStage = (Stage) this.btnRegistrarse.getScene().getWindow();
        myStage.close();
        }catch(Exception e){
            
        }
        
    }
}
