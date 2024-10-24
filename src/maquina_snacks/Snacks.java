package maquina_snacks;

import java.util.ArrayList;
import java.util.List;

public class Snacks {
    private static final List<Snack> snacks;

    // Bloque de tipo static inicializador
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 8000));
        snacks.add(new Snack("Chocorramo", 3000));
        snacks.add(new Snack("Gaseosa", 4500));
    }

    public static void agregarSnack(Snack snack) {
        snacks.add(snack);
    }

    public static void mostrarSnacks() {
        var inventarioSnacks = "";
        for (var snack : snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("---- Snacks en el inventario ---");
        System.out.println(inventarioSnacks);
    }

    public static List<Snack>getSnacks(){
        return snacks;
    }
}
