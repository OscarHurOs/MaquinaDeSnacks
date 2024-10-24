package maquina_snacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que representa la lógica de interacción de la máquina de snacks.
 * Permite al usuario comprar snacks, ver el ticket de compra y añadir nuevos productos.
 */
public class MaquinaSnacks {
    public static void main(String[] args) {

        try (var consola = new Scanner(System.in)) {
            new MaquinaSnacks().iniciarMaquina(consola);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void iniciarMaquina(Scanner consola) {
        boolean salir = false;
        List<Snack> productosComprados = new ArrayList<>();
        System.out.println("--- Maquina de snacks ---");
        Snacks.mostrarSnacks();

        while (!salir) {
            try {
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productosComprados);
            } catch (NullPointerException num) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    /**
     * Muestra el menú de opciones disponibles para el usuario y retorna su selección.
     *
     * @param consola Scanner para capturar la entrada del usuario.
     * @return Opción seleccionada por el usuario.
     */
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

    /**
     * Procesa la opción seleccionada por el usuario y ejecuta la acción correspondiente.
     *
     * @param opcion    Opción ingresada por el usuario.
     * @param consola   Scanner para la entrada del usuario.
     * @param productosComprados Lista de snacks comprados por el usuario.
     * @return true si el usuario decide salir; false en caso contrario.
     */
    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productosComprados) {

        var salir = false;
        switch (opcion) {
            case 1 -> comprarSnack(consola, productosComprados);
            case 2 -> mostrarTicket(productosComprados);
            case 3 -> agregarSnack(consola);
            case 4 -> {
                System.out.println("Regresa pronto!");
                salir = true;

            }
            default -> System.out.println("Opción invalida: " + opcion);
        }
        return salir;
    }

    /**
     * Permite al usuario comprar un snack ingresando su ID.
     *
     * @param consola   Scanner para capturar la entrada del usuario.
     * @param productosComprados Lista donde se almacenarán los snacks comprados.
     */
    private static void comprarSnack(Scanner consola, List<Snack> productosComprados) {
        System.out.println("--Que snack quieres comprar (id) ?");
        var idsnack = Integer.parseInt(consola.nextLine());
        var snackEncontrado = false;
        for (var snack : Snacks.getSnacks()) {
            if (idsnack == snack.getIdSnack()) {
                productosComprados.add(snack);
                System.out.println(" Ok, Snack agregado : " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado) {
            System.out.println(" Id de snack no encontrado: " + idsnack);
        }
    }

    /**
     * Muestra el ticket con los snacks comprados y el total a pagar.
     *
     * @param productosComprados Lista de snacks comprados.
     */
    private static void mostrarTicket(List<Snack> productosComprados) {
        var ticket = new StringBuilder("*** Ticket de venta ***");
        var total = 0.0;

        for (var producto : productosComprados) {
            ticket.append("\n\t-")
                    .append(producto.getNombre())
                    .append(" - $")
                    .append(producto.getPrecio());
            total += producto.getPrecio();
        }

        ticket.append("\n\tTotal -> $").append(total);
        System.out.println(ticket);
    }

    /**
     * Permite al usuario agregar un nuevo snack al inventario.
     *
     * @param consola Scanner para capturar los datos del nuevo snack.
     */
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
