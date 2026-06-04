package blass.academy.tests;

import blass.academy.modelos.Cliente;
import blass.academy.utils.BaseTest;
import blass.academy.utils.ExcelReader;
import blass.academy.utils.Logs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTests extends BaseTest {
    private List<Cliente> clientes;

    @BeforeEach
    void setUp() {
        Logs.info("Leyendo la lista de clientes");
        clientes = ExcelReader.leerExcel(
                "data.xlsx",
                "clientes",
                Cliente::parsearFilaExcel
        );
    }

    @Test
    void mayores40Test() {
        Logs.info("Encontrando mayores de 40");
        final var cantidad = clientes
                .stream()
                .filter(cliente -> cliente.edad() > 40)
                .count();

        Logs.info("Validar que hayan 17 elementos");
        assertEquals(17, cantidad, "No se tuvo la catidad esperada");

    }

    @Test
    void minimoNombresTest() {
        Logs.info("Encontrando el primer cliente según nombre");
        final var cliente = clientes
                .stream()
                .min(Comparator.comparing(Cliente::nombre))
                .orElseThrow();

        final var clienteEsperado = new Cliente("USR-17", "ANGELIQUE", "DURGAN", 52,
                "ANGELIQUE.DURGAN.31@HOTMAIL.COM", "SERBIA", "NORTHERN NEBRASKA ACADEMY");

        Logs.info("Verificando el primer cliente");
        assertEquals(clienteEsperado, cliente, "Cliente incorrecto");
    }

    @Test
    void encontrarIDTest() {
        Logs.info("Encontrando al cliente con el ID USR-22");
        final var clienteBuscado = clientes
                .stream()
                .filter(cliente -> cliente.id().equals("USR-22"))
                .findFirst()
                .orElseThrow();

        Logs.info("Verificando el cliente encontrado");
        assertAll(
                () -> assertEquals("USR-22", clienteBuscado.id(), "ID incorrecto"),
                () -> assertEquals("ROSANNE", clienteBuscado.nombre(), "Nombre incorrecto"),
                () -> assertEquals("KUB", clienteBuscado.apellido(), "Apellido incorrecto"),
                () -> assertEquals(31, clienteBuscado.edad(), "Edad incorrecto")
        );
    }
}
