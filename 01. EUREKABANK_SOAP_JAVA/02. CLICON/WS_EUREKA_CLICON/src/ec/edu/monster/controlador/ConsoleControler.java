/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import ec.edu.monster.modelo.EurekaServiceModel;
import ec.edu.monster.view.ConsoleView;
import ec.edu.monster.util.util;
import ec.edu.monster.ws.Movimiento;
import java.util.List;
/**
 *
 * @author ckan1
 */
public class ConsoleControler {
    private final ConsoleView view = new ConsoleView();
    private final EurekaServiceModel model = new EurekaServiceModel();
    private static final String VALID_USER = "MONSTER";
    private static final String VALID_PASSWORD_HASH = util.hashPassword("MONSTER9");

    public void start() {
        if (authenticateUser()) {
            mainMenu();
        } else {
            view.showExitMessage();
        }
    }

    private boolean authenticateUser() {
        for (int attempts = 3; attempts > 0; attempts--) {
            view.showLoginPrompt();
            String user = view.getInput();
            view.showPasswordPrompt();
            String password = view.getInput();

            if (VALID_USER.equals(user) && VALID_PASSWORD_HASH.equals(util.hashPassword(password))) {
                return true;
            } else {
                view.showInvalidLogin();
            }
        }
        return false;
    }

    private void mainMenu() {
        while (true) {
            view.showMenu();
            String option = view.getInput();

            switch (option) {
                case "1":
                    registerDeposit();
                    break;
                case "2":
                    consultMovements();
                    break;
                case "3":
                    view.showExitMessage();
                    return;
                default:
                    view.showError("Opción no válida.");
            }
        }
    }

    private void registerDeposit() {
        try {
            view.showDepositPrompt();
            String account = view.getInput();
            view.showAmountPrompt();
            double amount = Double.parseDouble(view.getInput());

            int result = model.regDeposito(account, amount);
            if (result == 1) {
                view.showDepositSuccess();
            } else {
                view.showDepositFailure();
            }
        } catch (Exception e) {
            view.showError("Error al registrar depósito: " + e.getMessage());
        }
    }

    private void consultMovements() {
        try {
            view.showAccountPrompt();
            String account = view.getInput();
            List<Movimiento> movements = model.traerMovimientos(account);
            if (movements.isEmpty()) {
                view.showError("No se encontraron movimientos.");
            } else {
                StringBuilder sb = new StringBuilder();
                movements.forEach(mov -> sb.append(String.format("Cuenta: %s, Monto: %.2f, Fecha: %s%n",
                        mov.getCuenta(), mov.getImporte(), mov.getFecha())));
                view.showMovements(sb.toString());
            }
        } catch (Exception e) {
            view.showError("Error al consultar movimientos: " + e.getMessage());
        }
    }
}
