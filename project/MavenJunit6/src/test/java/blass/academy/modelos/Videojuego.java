package blass.academy.modelos;

public record Videojuego(
        int id,
        String nombre,
        int epoca,
        double precio,
        int duracion,
        Genero genero,
        Empresa empresa
) {
    public static Videojuego parsearFilaExcel(Object[] data) {
        final var id = ((Double) data[0]).intValue();
        final var nombre = (String) data[1];
        final var epoca = ((Double) data[2]).intValue();
        final var precio = (Double) data[3];
        final var duracion = ((Double) data[4]).intValue();
        final var genero = Genero.valueOf((String) data[5]);
        final var empresa = Empresa.valueOf((String) data[6]);

        return new Videojuego(id, nombre, epoca, precio, duracion, genero, empresa);
    }

    public enum Genero {
        ACCION,
        TERROR,
        COMEDIA
    }

    public enum Empresa {
        XBOX,
        PLAYSTATION,
        NINTENDO
    }
}
