package TPFinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemaLogger {
    private static final String LOG_FILE = "C:\\Users\\smnvl\\Documents\\Facultad\\EDAT\\TPFinal\\Registro.log";

    // Método para eliminar el contenido del archivo de log
    public static void eliminarContenidoLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE))) {
            // Abrir el archivo en modo de escritura sin append trunca el archivo a cero
            writer.write(""); // Opcional: escribe una cadena vacía para asegurarse de que el archivo esté vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para escribir en el archivo de log
    public static void escribirLog(String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(timestamp + " - " + mensaje + '\n');
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para registrar operaciones específicas
    public static void registrarInsercion(String descripcion) {
        escribirLog("Inserción: " + descripcion);
    }

    public static void registrarEliminacion(String descripcion) {
        escribirLog("Eliminación: " + descripcion);
    }

    public static void registrarConsulta(String descripcion) {
        escribirLog("Consulta: " + descripcion);
    }
}
