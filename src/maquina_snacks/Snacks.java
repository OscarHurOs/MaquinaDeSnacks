package maquina_snacks;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el inventario de snacks.
 * Permite agregar nuevos snacks y consultar la lista disponible.
 */
public class Snacks {
    private static final List<Snack> snacks = new ArrayList<>();

    // Bloque estático para inicializar el inventario con algunos productos por defecto.
    static {
        snacks.add(new Snack("Papas", 8000));
        snacks.add(new Snack("Chocorramo", 3000));
        snacks.add(new Snack("Gaseosa", 4500));
    }

    /**
     * Agrega un nuevo snack al inventario.
     *
     * @param snack Snack a agregar.
     */
    public static void agregarSnack(Snack snack) {
        snacks.add(snack);
    }

    /**
     * Muestra los snacks disponibles en el inventario.
     * Imprime cada snack en la consola.
     */
    public static void mostrarSnacks() {
        System.out.println("---- Snacks en el inventario ----");
        snacks.forEach(System.out::println);
    }

    /**
     * Retorna una lista  con los snacks disponibles.     *
     *
     * @return Lista inmutable de snacks.
     */
    public static List<Snack> getSnacks() {
        return snacks;
    }
}
