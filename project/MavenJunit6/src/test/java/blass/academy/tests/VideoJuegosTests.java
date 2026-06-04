package blass.academy.tests;

import blass.academy.modelos.Videojuego;
import blass.academy.utils.BaseTest;
import blass.academy.utils.JsonManager;
import blass.academy.utils.Logs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoJuegosTests extends BaseTest {
    private List<Videojuego> videojuegos;

    @BeforeEach
    void setUp() {
        Logs.info("Leyendo la lisa de videojuegos");
        videojuegos = JsonManager.leerListaJson(
                "videojuegos.json",
                Videojuego.class
        );
    }

    @Test
    void xboxTest() {
        Logs.info("Obteniendo la cantidad de videojuegos de XBOX");
        final var cantidad = videojuegos
                .stream()
                .filter(videojuego -> videojuego.empresa() == Videojuego.Empresa.XBOX)
                .count();

        Logs.info("Verificar que la cantidad sea 7");
        assertEquals(7, cantidad, "No se tuvo la catidad esperada");
    }

    @Test
    void mayorDuracionTest() {
        Logs.info("Obteniendo el videojuegos con mayor duración");
        final var videojuegoLargo = videojuegos
                .stream()
                .max(Comparator.comparing(Videojuego::duracion))
                .orElseThrow();

        final var videojuegoEsperado = new Videojuego(3, "BLUE PROTOCOL", 2008, 57.45,
                60, Videojuego.Genero.TERROR, Videojuego.Empresa.XBOX);
        Logs.info("Verificando el videojuego más largo");
        assertEquals(videojuegoEsperado, videojuegoLargo, "Videojuego incorrecto");
    }

    @Test
    void encontrarIDTest() {
        Logs.info("Obteniendo el videojuegos con ID 14");
        final var videojuegoBuscado = videojuegos
                .stream()
                .filter(videojuego -> videojuego.id() == 14)
                .findFirst()
                .orElseThrow();

        Logs.info("Mostrando el videojuego encontrado");
        System.out.println(videojuegoBuscado);
    }
}
