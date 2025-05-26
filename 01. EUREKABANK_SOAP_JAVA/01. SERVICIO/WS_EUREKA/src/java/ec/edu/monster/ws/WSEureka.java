/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.ws;

import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.servicio.EurekaService;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author ckan1
 */
@WebService(serviceName = "WSEureka")
public class WSEureka {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "traerMovimientos")
    @WebResult(name = "movimiento")
    public List<Movimiento> traerMovimientos(@WebParam(name = "cuenta") String cuenta) {
        List<Movimiento> lista;
        
        //proceso
        try {
            //recuperar moviemintos
            EurekaService service =new EurekaService();
            lista = service.leerMovimientos(cuenta);
        } catch (Exception e) {
            //en caso de error, retorne una lista vacia
            lista = new ArrayList<>();
        }
        
        //retorno
        return lista;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "regDeposito")
    @WebResult(name = "estado")
    public int regDeposito(@WebParam(name = "cuenta") String cuenta, @WebParam(name = "importe") double importe) {
        int estado;
        
        //proceso
        String codEmp = "0001";
        try {
            EurekaService service = new EurekaService();
            service.registrarDeposito(cuenta, importe, codEmp);
            estado = 1;
        } catch (Exception e) {
            estado = -1;
        }
        
        //retorno
        return estado;
    }
}
