
package modelo;

/**
 *
 * @author raul_correa
 */
public class Usuario {
    private int id;
    private String nombre;
    private String usuario;
    private String contraseña;
    private String tipoUsuario;

    public Usuario(int id, String nombre, String usuario, String contraseña, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
