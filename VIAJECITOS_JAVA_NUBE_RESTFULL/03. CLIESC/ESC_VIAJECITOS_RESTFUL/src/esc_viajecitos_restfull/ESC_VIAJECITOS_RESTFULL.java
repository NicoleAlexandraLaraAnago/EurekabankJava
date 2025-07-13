/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esc_viajecitos_restfull;

import ec.edu.restfull.cliente.LoginView;

/**
 *
 * @author Sebastian
 */
public class ESC_VIAJECITOS_RESTFULL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
    }
    

