

package edu.cerp.checkin;

import edu.cerp.checkin.logic.SesionService;
import edu.cerp.checkin.ui.CheckInGUI;

public class App {

    public static void main(String[] args) {

        SesionService service = new SesionService();

        // Si se ejecuta con el parámetro --gui, abre la interfaz gráfica
        if (args.length > 0 && args[0].equalsIgnoreCase("--gui")) {
            System.out.println("Ejecutando en modo gráfico...");
            CheckInGUI.show(service);
        } else {
            System.out.println("Ejecutando en modo consola...");
            // Código del modo consola
            service.public static void main(String[] args) {
    // Creamos el servicio principal del sistema
    edu.cerp.checkin.logic.SesionService service = new edu.cerp.checkin.logic.SesionService();

    // Si el programa se ejecuta con el parámetro --gui, abre la interfaz gráfica
    if (args.length > 0 && args[0].equalsIgnoreCase("--gui")) {
        System.out.println("Ejecutando en modo gráfico...");
        new edu.cerp.checkin.ui.CheckInGUI();
    } else {
        System.out.println("Ejecutando en modo consola...");
        // Acá va tu código original de consola
        service.iniciar();
    }
}

