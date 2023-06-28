
package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
/**
 *
 * @author raul_correa
 */
public class Conexion {
    
    Connection cn;
    
    public Connection ConectarseBD(){     
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/login_java_mysql","root","");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("ERROR DE CONEXION");
            alert.showAndWait();
        }
        return cn;
    }
    
    
} 
    

    





