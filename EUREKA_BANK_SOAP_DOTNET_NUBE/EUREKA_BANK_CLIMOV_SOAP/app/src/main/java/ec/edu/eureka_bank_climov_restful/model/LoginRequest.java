package ec.edu.eureka_bank_climov_restful.model;

public class LoginRequest {
    private String usuario;
    private String clave;

    public LoginRequest(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }
}
