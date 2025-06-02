package modelo;

public class Usuario {
    private int id;  // 👈 Agregado
    private String nombre;
    private String correo;
    private String password;
    private String rol;

    // Constructor (opcional)
    public Usuario() {}

    public Usuario(int id, String nombre, String correo, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    public int getId() {  // 👈 Getter para id
        return id;
    }

    public void setId(int id) {  // 👈 Setter para id
        this.id = id;
    }

    // Resto de getters/setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
