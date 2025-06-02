/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Vuelo;
import servicio.VueloServicio;

import java.io.IOException;
import java.util.List;

public class VueloControlador {
    private VueloServicio vueloServicio;

    public VueloControlador() {
        this.vueloServicio = new VueloServicio();
    }

    public List<Vuelo> buscarVuelos(String origen, String destino, String fecha) {
        try {
            return vueloServicio.buscarVuelos(origen, destino, fecha);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

