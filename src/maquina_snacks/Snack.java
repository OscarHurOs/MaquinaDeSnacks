package maquina_snacks;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa un snack con un ID, nombre y precio.
 */
public class Snack implements Serializable {

    private static int contadorSnacks = 0;
    private final int idSnack;
    private String nombre;
    private double precio;

    /**
     * Crea un nuevo snack con el nombre y precio especificados.
     * @param nombre Nombre del snack.
     * @param precio Precio del snack.
     */
    public Snack(String nombre, double precio) {
        this.idSnack = ++contadorSnacks;
        setNombre(nombre);
        setPrecio(precio);
    }

    public int getIdSnack() {
        return idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del snack. No se permite un nombre vacío.
     * @param nombre Nombre del snack.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del snack. No se permite un valor negativo.
     * @param precio Precio del snack.
     */
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Snack{" +
               "idSnack=" + idSnack +
               ", nombre='" + nombre + '\'' +
               ", precio=" + precio +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
