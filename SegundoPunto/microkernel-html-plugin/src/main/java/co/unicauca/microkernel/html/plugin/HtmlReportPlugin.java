/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package co.unicauca.microkernel.html.plugin;


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
public class HtmlReportPlugin implements IReportPlugin{

 @Override
    public String generateReport(List<Project> data) {
        
        // Generar ruta automáticamente
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "Reporte_Proyectos_" + timestamp + ".html";
        String filePath = getDownloadsPath() + File.separator + fileName;
        
        System.out.println("Generando reporte HTML automático...");
        System.out.println("Archivo: " + fileName);
        System.out.println("Ubicación: " + filePath);
        System.out.println("Proyectos a procesar: " + (data != null ? data.size() : 0));

        try (FileWriter writer = new FileWriter(filePath, java.nio.charset.StandardCharsets.UTF_8)) {
            
            // Generar HTML completo
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html lang=\"es\">\n");
            writer.write("<head>\n");
            writer.write("    <meta charset=\"UTF-8\">\n");
            writer.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            writer.write("    <title>Reporte de Proyectos de Grado</title>\n");
            writer.write("    <style>\n");
            writer.write(getCSS());
            writer.write("    </style>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            
            // Encabezado
            writer.write("    <div class=\"header\">\n");
            writer.write("        <h1>Reporte de Proyectos de Grado</h1>\n");
            writer.write("        <p class=\"date\">Generado el: " + 
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "</p>\n");
            writer.write("    </div>\n\n");
            
            // Tabla
            writer.write("    <div class=\"table-container\">\n");
            writer.write("        <table>\n");
            writer.write("            <thead>\n");
            writer.write("                <tr>\n");
            writer.write("                    <th>ID</th>\n");
            writer.write("                    <th>Nombre del Proyecto</th>\n");
            writer.write("                    <th>Fecha Aprobación Formato A</th>\n");
            writer.write("                    <th>Estudiante(s)</th>\n");
            writer.write("                    <th>Profesor</th>\n");
            writer.write("                    <th>Tipo</th>\n");
            writer.write("                    <th>Programa</th>\n");
            writer.write("                </tr>\n");
            writer.write("            </thead>\n");
            writer.write("            <tbody>\n");
            
            // Datos de la tabla
            if (data != null && !data.isEmpty()) {
                for (Project p : data) {
                    writer.write("                <tr>\n");
                    writer.write("                    <td>" + escapeHtml(String.valueOf(p.getId())) + "</td>\n");
                    writer.write("                    <td>" + escapeHtml(p.getNombre()) + "</td>\n");
                    writer.write("                    <td>" + escapeHtml(p.getFechaAprovacionFormatoA()) + "</td>\n");
                    
                    // Estudiantes (concatenar si hay dos)
                    String estudiantes = escapeHtml(p.getEstudiante1());
                    if (p.getEstudiante2() != null && !p.getEstudiante2().equals("null") && !p.getEstudiante2().trim().isEmpty()) {
                        estudiantes += ", " + escapeHtml(p.getEstudiante2());
                    }
                    writer.write("                    <td>" + estudiantes + "</td>\n");
                    
                    writer.write("                    <td>" + escapeHtml(p.getProfesor()) + "</td>\n");
                    writer.write("                    <td>" + escapeHtml(p.getTipo()) + "</td>\n");
                    writer.write("                    <td>" + escapeHtml(p.getPrograma()) + "</td>\n");
                    writer.write("                </tr>\n");
                }
            } else {
                writer.write("                <tr>\n");
                writer.write("                    <td colspan=\"7\" class=\"no-data\">No hay proyectos para mostrar</td>\n");
                writer.write("                </tr>\n");
            }
            
            writer.write("            </tbody>\n");
            writer.write("        </table>\n");
            writer.write("    </div>\n\n");
            
            // Pie
            writer.write("    <div class=\"footer\">\n");
            writer.write("        <p><strong>Total de proyectos:</strong> " + (data != null ? data.size() : 0) + "</p>\n");
            writer.write("    </div>\n");
            
            writer.write("</body>\n");
            writer.write("</html>");
            
            System.out.println("HTML generado exitosamente!");            
            return filePath;

        } catch (IOException e) {
            System.err.println("Error al generar HTML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Escapa caracteres especiales para HTML válido
     */
    private String escapeHtml(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "-";
        }
        
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
    
    /**
     * CSS para el reporte HTML
     */
    private String getCSS() {
        return """
            body {
                margin: 0;
                padding: 20px;
                background-color: #f5f5f5;
            }
            
            .header {
                text-align: center;
                margin-bottom: 30px;
                background: white;
                padding: 20px;
            }
            
            h1 {
                color: black;
                margin: 0 0 10px 0;
                font-size: 2em;
            }
            
            .date {
                color: #666;
                font-size: 0.9em;
                margin: 0;
            }
            
            .table-container {
                background: white;
                overflow: hidden;
                overflow-x: auto;
            }
            
            table {
                width: 100%;
                border-collapse: collapse;
                font-size: 0.9em;
            }
            
            th, td {
                padding: 12px 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            
            th {
                background-color: #34495e;
                color: white;
                font-weight: 600;
                position: sticky;
                top: 0;
            }
                        
            .no-data {
                text-align: center;
                font-style: italic;
                color: #666;
            }
            
            .footer {
                margin-top: 20px;
                background: white;
                padding: 15px 20px;
            }
            
            .footer p {
                margin: 0;
            }

        """;
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
