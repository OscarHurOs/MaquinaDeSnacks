package maquina_snacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    private static void maquinaSnacks() {
        var salir = false;
        var consola = new Scanner(System.in);

        List<Snack> productos = new ArrayList<>();
        System.out.println("--- Maquina de snacks ---");
        Snacks.mostrarSnacks();

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }


    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar Nuevo snack
                4. Salir
                Elige una opcion :
                """);
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos) {

        var salir = false;
        switch (opcion) {
            case 1 -> comprarSnack(consola, productos);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola);
            case 4 -> {
                System.out.println("Regresa pronto!");
                salir=true;

            }
            default -> System.out.println("Opción invalida: "+ opcion);
        }
        return salir;
    }


    private static void comprarSnack(Scanner consola, List<Snack> productos) {
        System.out.println("--Que snack quieres comprar (id) ?");
        var idsnack = Integer.parseInt(consola.nextLine());
        var snackEncontrado = false;
        for (var snack : Snacks.getSnacks()) {
            if (idsnack == snack.getIdSnack()) {
                productos.add(snack);
                System.out.println(" Ok, Snack agregado : " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado) {
            System.out.println(" Id de snack no encontrado: " + idsnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos) {
        var ticket = new StringBuilder("*** Ticket de venta ***");
        var total = 0.0;

        for (var producto : productos) {
            ticket.append("\n\t-")
                    .append(producto.getNombre())
                    .append(" - $")
                    .append(producto.getPrecio());
            total += producto.getPrecio();
        }

        ticket.append("\n\tTotal -> $").append(total);
        System.out.println(ticket);
    }


    private static void agregarSnack(Scanner consola) {
        System.out.println("Nombre del snack : ");
        var nombre = consola.nextLine();
        System.out.println("Precio del snack : ");
        var precio = Double.parseDouble(consola.nextLine());
        Snacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Tu snack se ha agregado correctamente");
        Snacks.mostrarSnacks();
    }
}
