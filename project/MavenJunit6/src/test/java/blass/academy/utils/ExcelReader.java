package blass.academy.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ExcelReader {
    public static <T> List<T> leerExcel(String fileName, String sheetName, Function<Object[], T> parser) {
        final var path = Path.of(Config.get("path.excel"), fileName);
        return leerExcel(path, sheetName, parser);
    }

    public static <T> List<T> leerExcel(Path path, String sheetName, Function<Object[], T> parser) {
        final var data = readData(path, sheetName);
        return Arrays.stream(data)
                .map(parser)
                .toList();
    }

    private static Object[][] readData(Path path, String sheetName) {
        try (var workbook = getWorkBook(path)) {
            final var sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' no encontrado");
            }

            final var rows = sheet.getPhysicalNumberOfRows();
            if (rows <= 1) {
                return new Object[0][0];
            }

            final var columns = sheet.getRow(0).getPhysicalNumberOfCells();
            final var data = new Object[rows - 1][columns];

            for (var i = 1; i < rows; i++) {
                final var actualRow = sheet.getRow(i);

                for (var j = 0; j < columns; j++) {
                    final var actualCell = actualRow.getCell(j);
                    data[i - 1][j] = getSingleValue(actualCell);
                }
            }

            return data;
        } catch (IOException ioException) {
            Logs.error("Error al procesar el excel: %s", ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }

    private static XSSFWorkbook getWorkBook(Path path) {
        try {
            if (!Files.exists(path)) {
                throw new IOException("El archivo no existe: " + path);
            }
            return new XSSFWorkbook(Files.newInputStream(path));
        } catch (IOException ioException) {
            Logs.error("Error al abrir el excel: %s", ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }

    private static Object getSingleValue(XSSFCell cell) {
        if (cell == null) {
            return null;
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case NUMERIC -> cell.getNumericCellValue();
            case BLANK -> null;
            default -> cell.toString();
        };
    }
}