package blass.academy.tests;

import blass.academy.modelos.Cliente;
import blass.academy.utils.BaseTest;
import blass.academy.utils.ExcelReader;
import blass.academy.utils.Logs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

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

        Logs.info("Mostrando cantidad en consola");
        System.out.println(cantidad);
    }

    @Test
    void minimoNombresTest() {
        Logs.info("Encontrando el primer cliente según nombre");
        final var cliente = clientes
                .stream()
                .min(Comparator.comparing(Cliente::nombre))
                .orElseThrow();

        Logs.info("Mostrando al primer cliente en consola");
        System.out.println(cliente);
    }

    @Test
    void encontrarIDTest() {
        Logs.info("Encontrando al cliente con el ID USR-22");
        final var clienteBuscado = clientes
                .stream()
                .filter(cliente -> cliente.id().equals("USR-22"))
                .findFirst()
                .orElseThrow();

        Logs.info("Mostrando al cliente encontrado");
        System.out.println(clienteBuscado);
    }
}
