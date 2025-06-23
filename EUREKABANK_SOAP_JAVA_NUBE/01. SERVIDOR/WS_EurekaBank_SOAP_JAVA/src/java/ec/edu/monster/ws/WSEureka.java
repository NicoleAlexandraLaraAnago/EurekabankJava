/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.ws;

import ec.edu.monster.controlador.Login;
import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.modelo.ResultadoOperacion;
import ec.edu.monster.servicios.EurekaService;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author leito
 */
@WebService(serviceName = "WSEureka")
public class WSEureka {

@WebMethod(operationName = "traerMovimientos")
@WebResult(name = "movimiento")
public List<Movimiento> traerMovimientos(@WebParam(name = "cuenta") String cuenta) {
    List<Movimiento> lista = new ArrayList<>();

    try {
        EurekaService service = new EurekaService();
        List<Movimiento> movimientos = service.leerMovimientos(cuenta);

        for (Movimiento mov : movimientos) {
            String tipo = mov.getTipo();
            if (tipo.equals("003") || tipo.equals("004") || tipo.equals("009")) {
                lista.add(mov);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        lista = new ArrayList<>();
    }

    return lista;
}


    /**
     * Web service operation
     * @param cuenta
     * @param tipoCodigo
     * @param importe
     * @return Estado, 1 o -1
     */
    @WebMethod(operationName = "regDeposito")
    @WebResult(name = "resultado")
    public int regDeposito(@WebParam(name = "cuenta") String cuenta, @WebParam(name = "tipoCodigo") String tipoCodigo, @WebParam(name = "importe") double importe) {
        // Validación de parámetros
        if (cuenta == null || cuenta.isEmpty()) {
            return -1;
        }
        if (importe <= 0) {
            return -1;
        }

        // Proceso de registro de depósito
        String codEmp = "0001";
        try {
            EurekaService service = new EurekaService();
            service.registrarDeposito(cuenta, importe,tipoCodigo, codEmp);
            return 1;
        } catch (Exception e) {
            // Registrar el error para diagnóstico
            System.err.println("Error al registrar el depósito: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    @WebMethod(operationName = "login")
    @WebResult(name = "login")
    public int iniciarSesion(@WebParam(name = "usuario") String usuario, @WebParam(name = "contrasena") String contrasena) {
        try {
            Login service = new Login();
            
            if(service.IniciarSesion(usuario, contrasena)){
                return 1;
            }else{
                return -1;
            }
        } catch (Exception e) {
            // Registrar el error para diagnóstico
            System.err.println("Error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Web service operation
     * @param cuenta
     * @return El saldo de la cuenta
     */
    @WebMethod(operationName = "obtenerSaldo")
    @WebResult(name = "saldo")
    public double obtenerSaldo(@WebParam(name = "cuenta") String cuenta) {
        try {
            EurekaService service = new EurekaService();
            return service.obtenerSaldo(cuenta);
        } catch (Exception e) {
            // Registrar el error para diagnóstico
            System.err.println("Error al obtener el saldo: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    


}
