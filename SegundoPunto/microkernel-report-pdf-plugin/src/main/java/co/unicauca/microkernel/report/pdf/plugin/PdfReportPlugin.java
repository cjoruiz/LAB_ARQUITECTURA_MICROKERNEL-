/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package co.unicauca.microkernel.report.pdf.plugin;

import co.unicauca.microkerne.common.entities.Project;
import co.unicauca.microkerne.common.interfaces.IReportPlugin;
import java.io.File;
import java.util.List;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.TextAlignment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author crist
 */

public class PdfReportPlugin implements IReportPlugin {

    @Override
    public String generateReport(List<Project> data) {
 
        // Generar ruta automáticamente
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "Reporte_Proyectos_" + timestamp + ".pdf";
        String filePath = getDownloadsPath() + File.separator + fileName;
        
        System.out.println("Generando reporte PDF automático...");
        System.out.println(" Archivo: " + fileName);
        System.out.println("Ubicación: " + filePath);
        System.out.println("Proyectos a procesar: " + (data != null ? data.size() : 0));

        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título y metadatos
            document.add(new Paragraph("REPORTE DE PROYECTOS DE GRADO")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));
            
            document.add(new Paragraph("Generado el: " + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // Tabla
            float[] columnWidths = {1, 3, 2, 2, 2, 2, 2, 2};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            String[] headers = {"ID", "Nombre", "Fecha Aprobación", "Estudiante 1", "Estudiante 2", "Profesor", "Tipo", "Programa"};
            for (String header : headers) {
                table.addCell(new Cell().add(new Paragraph(header).setBold()));
            }

            // Datos
            if (data != null && !data.isEmpty()) {
                for (Project p : data) {
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getId()))));
                    table.addCell(new Cell().add(new Paragraph(p.getNombre() != null ? p.getNombre() : "-")));
                    table.addCell(new Cell().add(new Paragraph(p.getFechaAprovacionFormatoA() != null ? p.getFechaAprovacionFormatoA() : "-")));
                    table.addCell(new Cell().add(new Paragraph(p.getEstudiante1() != null ? p.getEstudiante1() : "-")));
                    table.addCell(new Cell().add(new Paragraph(p.getEstudiante2() != null && !p.getEstudiante2().equals("null") ? p.getEstudiante2() : "-")));
                    table.addCell(new Cell().add(new Paragraph(p.getProfesor() != null ? p.getProfesor() : "-")));
                    table.addCell(new Cell().add(new Paragraph(p.getTipo() != null ? p.getTipo() : "-")));
                    table.addCell(new Cell().add(new Paragraph(p.getPrograma() != null ? p.getPrograma() : "-")));
                }
            }

            document.add(table);
            document.add(new Paragraph("\nTotal de proyectos: " + (data != null ? data.size() : 0))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT));
            
            document.close();

            System.out.println("PDF generado exitosamente!");
            
            return filePath;

        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene la ruta de la carpeta Descargas del usuario
     * Funciona en Windows, Mac y Linux
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
            // Linux: /home/usuario/Downloads o /home/usuario/Descargas
            downloadsPath = userHome + File.separator + "Downloads";
            
            // Verificar si existe, sino crear
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