/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package co.unicauca.microkernel.json.plugin;

import co.unicauca.microkerne.common.entities.Project;
import co.unicauca.microkerne.common.interfaces.IReportPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author crist
 */

  public class JsonReportPlugin implements IReportPlugin {

    @Override
    public String generateReport(List<Project> data) {
        
        // Generar ruta automáticamente
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "Reporte_Proyectos_" + timestamp + ".json";
        String filePath = getDownloadsPath() + File.separator + fileName;
        
        System.out.println("Generando reporte JSON automático...");
        System.out.println("Archivo: " + fileName);
        System.out.println("Ubicación: " + filePath);
        System.out.println("Proyectos a procesar: " + (data != null ? data.size() : 0));

        try (FileWriter writer = new FileWriter(filePath, java.nio.charset.StandardCharsets.UTF_8)) {
            
            // Generar JSON manualmente (sin dependencias externas)
            writer.write("[\n");
            
            if (data != null && !data.isEmpty()) {
                for (int i = 0; i < data.size(); i++) {
                    Project p = data.get(i);
                    
                    writer.write("  {\n");
                    writer.write("    \"id\": \"" + escapeJson(String.valueOf(p.getId())) + "\",\n");
                    writer.write("    \"nombre\": \"" + escapeJson(p.getNombre()) + "\",\n");
                    writer.write("    \"fechaFormatoA\": \"" + escapeJson(p.getFechaAprovacionFormatoA()) + "\",\n");
                    writer.write("    \"estudiante1\": \"" + escapeJson(p.getEstudiante1()) + "\",\n");
                    
                    // Manejar estudiante2 que puede ser null
                    if (p.getEstudiante2() != null && !p.getEstudiante2().equals("null") && !p.getEstudiante2().trim().isEmpty()) {
                        writer.write("    \"estudiante2\": \"" + escapeJson(p.getEstudiante2()) + "\",\n");
                    } else {
                        writer.write("    \"estudiante2\": null,\n");
                    }
                    
                    writer.write("    \"profesor\": \"" + escapeJson(p.getProfesor()) + "\",\n");
                    writer.write("    \"tipo\": \"" + escapeJson(p.getTipo()) + "\",\n");
                    writer.write("    \"programa\": \"" + escapeJson(p.getPrograma()) + "\"\n");
                    
                    // Agregar coma excepto en el último elemento
                    if (i < data.size() - 1) {
                        writer.write("  },\n");
                    } else {
                        writer.write("  }\n");
                    }
                }
            }
            
            writer.write("]");
            
            System.out.println("JSON generado exitosamente!");
            
            return filePath;

        } catch (IOException e) {
            System.err.println("Error al generar JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Escapa caracteres especiales para JSON válido
     */
    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        
        return text.replace("\\", "\\\\")    // Escape backslashes
                  .replace("\"", "\\\"")     // Escape quotes
                  .replace("\n", "\\n")      // Escape newlines
                  .replace("\r", "\\r")      // Escape carriage returns
                  .replace("\t", "\\t");     // Escape tabs
    }

    /**
     * Obtiene la ruta de la carpeta Descargas del usuario
     */
    private static String getDownloadsPath() {
        String userHome = System.getProperty("user.home");
        String downloadsPath;
        
        // Detectar sistema operativo
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("win")) {

            downloadsPath = userHome + File.separator + "Downloads";
            
            // Verificar si existe, sino intentar en español
            File downloadsDir = new File(downloadsPath);
            if (!downloadsDir.exists()) {
                downloadsPath = userHome + File.separator + "Descargas";
            }
        } else if (os.contains("mac")) {
            // macOS: /Users/usuario/Downloads
            downloadsPath = userHome + File.separator + "Downloads";
        } else {
            // Linux: /home/usuario/Downloads
            downloadsPath = userHome + File.separator + "Downloads";
            
            File downloadsDir = new File(downloadsPath);
            if (!downloadsDir.exists()) {
                downloadsPath = userHome + File.separator + "Descargas";
            }
        }
        
        // Crear la carpeta si no existe
        File downloadsDir = new File(downloadsPath);
        if (!downloadsDir.exists()) {
            boolean created = downloadsDir.mkdirs();
            if (created) {
                System.out.println("Carpeta de descargas creada: " + downloadsPath);
            } else {
                System.out.println("No se pudo crear la carpeta de descargas, usando directorio home");
                downloadsPath = userHome;
            }
        }
        
        System.out.println("Usando carpeta de descargas: " + downloadsPath);
        return downloadsPath;
    }
}

