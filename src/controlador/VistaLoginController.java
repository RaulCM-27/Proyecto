/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author raul_correa
 */
public class VistaLoginController implements Initializable {

    Conexion con = new Conexion();
    Connection cn = con.ConectarseBD();

    @FXML
    private Button btnIniciar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Button btnRegistrar;

    @FXML
    private void eventKey(KeyEvent event) {
        String c = event.getCharacter();

        if (c.equalsIgnoreCase(" ")) {
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

        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if (!usuario.equals("") || !contraseña.equals("")) {

            try {
                PreparedStatement ps = (PreparedStatement) cn.prepareStatement("SELECT tipoUsuario from usuario WHERE usuario='"+usuario+"' and contraseña='"+contraseña+"'");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String tipoUsuario = rs.getString("tipoUsuario");

                    //METODO QUE ME ABRE PRIVILEGIOS DE VENDEDOR
                    if (tipoUsuario.equalsIgnoreCase("vendedor")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaVendedor.fxml"));

                        Parent root = loader.load();

                        VistaVendedorController controlador = loader.getController();

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();                       
                    }

                    //METODO QUE ME ABRE PRIVILEGIOS DE ADMIN
                    if (tipoUsuario.equalsIgnoreCase("admin")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaAdmin.fxml"));

                        Parent root = loader.load();
                        //llamar controlador 
                        VistaAdminController controlador = loader.getController();

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("USUARIO Y CONTRASEÑA INCORRECTOS");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("NO SE PUEDE INICIAR SESION");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("POR FAVOR COMPLETAR LOS CAMPOS");
            alert.showAndWait();
        }       
    }

    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaRegistro.fxml"));

        Parent root = loader.load();

        VistaRegistroController controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e -> controlador.closeWindows());
        
        Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
        myStage.close();
    }
    
    

}
 