
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author raul_correa
 */
public class Conexion {
    
    Connection cn;
    
    public Connection ConectarseBD(){     
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/login_java_mysql","root","");
            JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXION!"+e);
        }
        return cn;
    }
    
    
} 
    

    





