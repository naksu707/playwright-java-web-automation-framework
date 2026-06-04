package blass.academy.utils;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T leerJson(String fileName, Class<T> clazz) {
        final var path = Path.of(Config.get("path.json"), fileName);
        return leerJson(path, clazz);
    }

    public static <T> T leerJson(Path path, Class<T> clazz) {
        try {
            return mapper.readValue(Files.newInputStream(path), clazz);
        } catch (IOException ioException) {
            Logs.error("Error al leer JSON: %s", ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }

    public static <T> List<T> leerListaJson(String fileName, Class<T> clazz) {
        final var path = Path.of(Config.get("path.json"), fileName);
        return leerListaJson(path, clazz);
    }

    public static <T> List<T> leerListaJson(Path path, Class<T> clazz) {
        try {
            return mapper.readValue(
                    Files.newInputStream(path),
                    mapper.getTypeFactory().constructCollectionType(List.class, clazz)
            );
        } catch (IOException ioException) {
            Logs.error("Error al leer JSON lista: %s", ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }

    public static <T> T parsearJson(String content, Class<T> clazz) {
        return mapper.readValue(content, clazz);
    }

    public static <T> List<T> parsearListaJson(String content, Class<T> clazz) {
        return mapper.readValue(
                content,
                mapper.getTypeFactory().constructCollectionType(List.class, clazz)
        );
    }

    public static <T> void escribirJson(String nombreArchivo, T object) {
        final var path = Path.of(Config.get("path.output"), nombreArchivo);
        escribirJson(path, object);
    }

    public static <T> void escribirJson(Path path, T object) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            mapper
                    .writer(SerializationFeature.INDENT_OUTPUT)
                    .writeValue(Files.newOutputStream(path), object);
            System.out.printf("Json creado en: %s%n", path);
        } catch (IOException ioException) {
            Logs.error("Error al escribir JSON: %s", ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }
}