package blass.academy.data;

import blass.academy.modelos.Cliente;
import blass.academy.modelos.Videojuego;
import blass.academy.utils.ExcelReader;
import blass.academy.utils.JsonManager;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class CustomData {
    public static List<Arguments> obtenerParametrosClientes() {
        final var clientes = JsonManager.leerListaJson(
                "clientes.json",
                Cliente.class
        );
        return clientes.stream().map(Arguments::of).toList();
    }

    public static List<Arguments> obtenerParametrosVideojuegos() {
        final var videojuegos = ExcelReader.leerExcel(
                "data.xlsx",
                "videojuegos",
                Videojuego::parsearFilaExcel
        );
        return videojuegos.stream().map(Arguments::of).toList();
    }
}
