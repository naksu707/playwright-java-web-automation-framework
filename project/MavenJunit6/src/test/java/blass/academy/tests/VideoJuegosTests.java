package blass.academy.tests;

import blass.academy.modelos.Videojuego;
import blass.academy.utils.JsonManager;
import blass.academy.utils.Logs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class VideoJuegosTests {
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
        final var catidad = videojuegos
                .stream()
                .filter(videojuego -> videojuego.empresa() == Videojuego.Empresa.XBOX)
                .count();

        Logs.info("Mostrando la cantidad en consola");
        System.out.println(catidad);
    }

    @Test
    void mayorDuracionTest() {
        Logs.info("Obteniendo el videojuegos con mayor duración");
        final var videojuegoLargo = videojuegos
                .stream()
                .max(Comparator.comparing(Videojuego::duracion))
                .orElseThrow();

        Logs.info("Mostrando al videojuego más en consola");
        System.out.println(videojuegoLargo);
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
