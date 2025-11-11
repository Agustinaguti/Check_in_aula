package edu.cerp.checkin;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class App {

    // ==== Clase interna para representar un CheckIn ====
    static class CheckIn {
        private String alumnoNombre;
        private String fechaHora;

        public CheckIn(String alumnoNombre, String fechaHora) {
            this.alumnoNombre = alumnoNombre;
            this.fechaHora = fechaHora;
        }

        public String getAlumnoNombre() {
            return alumnoNombre;
        }

        public String getFechaHora() {
            return fechaHora;
        }

        @Override
        public String toString() {
            return alumnoNombre + ";" + fechaHora;
        }

        public static CheckIn fromString(String linea) {
            String[] partes = linea.split(";");
            if (partes.length == 2) {
                return new CheckIn(partes[0], partes[1]);
            }
            return null;
        }
    }

    // ==== Métodos para guardar y leer del archivo ====
    private static final String FILE_PATH = "checkins.txt";

    private static List<CheckIn> loadAll() {
        List<CheckIn> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                CheckIn c = CheckIn.fromString(linea);
                if (c != null) lista.add(c);
            }
        } catch (IOException e) {
            // si no existe el archivo, no hace nada
        }
        return lista;
    }

    private static void saveAll(List<CheckIn> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (CheckIn c : lista) {
                pw.println(c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==== Programa principal ====
    public static void main(String[] args) {

        List<CheckIn> lista = loadAll();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENU CHECK-IN AULA =====");
            System.out.println("1) Registrar Check-in");
            System.out.println("2) Ver registros");
            System.out.println("3) Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre del alumno: ");
                    String nombre = sc.nextLine();
                    String fecha = LocalDateTime.now().toString();
                    CheckIn nuevo = new CheckIn(nombre, fecha);
                    lista.add(nuevo);
                    saveAll(lista);
                    System.out.println("✅ Check-in guardado correctamente.");
                    break;

                case "2":
                    System.out.println("\n📋 Lista de check-ins:");
                    for (CheckIn c : lista) {
                        System.out.println(c.getAlumnoNombre() + " - " + c.getFechaHora());
                    }
                    break;

                case "3":
                    saveAll(lista);
                    System.out.println("💾 Datos guardados. ¡Hasta luego!");
                    salir = true;
                    break;

                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        }
        sc.close();
    }
}