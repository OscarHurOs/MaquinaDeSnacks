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

        switch (opcion) {
            case 1 -> comprarSnack(consola, productosComprados);
            case 2 -> mostrarTicket(productosComprados);
            case 3 -> agregarSnack(consola);
            case 4 -> {
                System.out.println("Regresa pronto!");
                return true;

            }
            default -> System.out.println("Opción invalida: " + opcion);
        }
        return false;
    }

    /**
     * Permite al usuario comprar un snack ingresando su ID.
     *
     * @param consola   Scanner para capturar la entrada del usuario.
     * @param productosComprados Lista donde se almacenarán los snacks comprados.
     */
    private static void comprarSnack(Scanner consola, List<Snack> productosComprados) {
        System.out.println("¿Qué snack quieres comprar (id)?");
        int idSnack = Integer.parseInt(consola.nextLine());

        Snacks.getSnacks().stream()
                .filter(snack -> snack.getIdSnack() == idSnack)
                .findFirst()
                .ifPresentOrElse(
                        snack -> {
                            productosComprados.add(snack);
                            System.out.println("Snack agregado: " + snack);
                        },
                        () -> System.out.println("Id de snack no encontrado: " + idSnack)
                );
    }

    /**
     * Muestra el ticket con los snacks comprados y el total a pagar.
     *
     * @param productosComprados Lista de snacks comprados.
     */
    private static void mostrarTicket(List<Snack> productosComprados) {
        StringBuilder ticket = new StringBuilder("*** Ticket de Venta ***\n");
        double total = productosComprados.stream()
                .mapToDouble(Snack::getPrecio)
                .sum();

        productosComprados.forEach(producto ->
                ticket.append("- ").append(producto.getNombre())
                        .append(" - $").append(producto.getPrecio()).append("\n")
        );

        ticket.append("Total: $").append(total);
        System.out.println(ticket);
    }

    /**
     * Permite al usuario agregar un nuevo snack al inventario.
     *
     * @param consola Scanner para capturar los datos del nuevo snack.
     */
    private static void agregarSnack(Scanner consola) {
        System.out.print("Nombre del snack: ");
        String nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        double precio = Double.parseDouble(consola.nextLine());

        Snacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Snack agregado correctamente.");
        Snacks.mostrarSnacks();
    }
}
