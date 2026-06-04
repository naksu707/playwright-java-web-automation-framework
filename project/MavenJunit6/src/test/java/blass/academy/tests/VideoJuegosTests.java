package blass.academy.tests;

import blass.academy.anotaciones.Regression;
import blass.academy.anotaciones.Smoke;
import blass.academy.modelos.Videojuego;
import blass.academy.utils.BaseTest;
import blass.academy.utils.JsonManager;
import blass.academy.utils.Logs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
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
    @Regression
    @Smoke
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
    @Smoke
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
    @Regression
    void encontrarIDTest() {
        Logs.info("Obteniendo el videojuegos con ID 14");
        final var videojuegoBuscado = videojuegos
                .stream()
                .filter(videojuego -> videojuego.id() == 14)
                .findFirst()
                .orElseThrow();

        Logs.info("Verificando el viedo encontrado");
        assertAll(
                () -> assertEquals(14, videojuegoBuscado.id(), "ID incorrecto"),
                () -> assertEquals("METROID PRIME 4", videojuegoBuscado.nombre(), "Nombre incorrecto"),
                () -> assertEquals(Videojuego.Genero.ACCION, videojuegoBuscado.genero(), "Genero incorrecto"),
                () -> assertEquals(Videojuego.Empresa.NINTENDO, videojuegoBuscado.empresa(), "Empresa incorrecto")
        );
    }

    @Test
    @Disabled("Test deshabilitado por falta de data")
    void desabilitadoTest() {
        Logs.info("Hola videojuegos");
    }
}
