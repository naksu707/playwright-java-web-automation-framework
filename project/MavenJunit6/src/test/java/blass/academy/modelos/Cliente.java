package blass.academy.modelos;

public record Cliente(
        String id,
        String nombre,
        String apellido,
        int edad,
        String email,
        String pais,
        String universidad
) {
    public static Cliente parsearFilaExcel(Object[] data) {
        final var id = (String) data[0];
        final var nombre = (String) data[1];
        final var apellido = (String) data[2];
        final var edad = ((Double) data[3]).intValue();
        final var email = (String) data[4];
        final var pais = (String) data[5];
        final var universidad = (String) data[6];

        return new Cliente(id, nombre, apellido, edad, email, pais, universidad);
    }
}
