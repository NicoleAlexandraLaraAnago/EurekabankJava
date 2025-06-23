/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.pruebas;

import ec.edu.monster.service.EurekaService;



/**
 *
 * @author leito
 */
public class PruebaDeposito {

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            //datos
            String cuenta = "00100001";
            String tipoCodigo = "004";
            double importe = 18;

            //proceso
            EurekaService service = new EurekaService();
            service.regDeposito(cuenta, tipoCodigo, importe);
            System.out.println("Proceso ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
