/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.microkernel.core.presentation;

import co.unicauca.microkerne.common.entities.Project;
import co.unicauca.microkerne.common.entities.Report;
import co.unicauca.microkernel.core.business.ProjectService;
import co.unicauca.microkernel.core.business.ReportService;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class Console {
        private ProjectService projectService;
    private ReportService reportService;

    private Scanner scanner;

    public Console(){
        projectService = new ProjectService();
        reportService = new ReportService();
        scanner = new Scanner(System.in);
    }

    public void start() {

        int option;

        System.out.println("Aplicación de reportes");

        do {

            System.out.println();
            System.out.println("1. Crear reporte.");
            System.out.println("2. Salir.");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    handleDeliveryCostOption();
                    break;
            }

        } while(option != 2);

        System.out.println("Aplicación terminada");
    }

    private void handleDeliveryCostOption(){

        //Mostrar producto para el cual se calculará el envío.
        List<Project> projects = projectService.getAll();

        System.out.println("Proyectos existentes: ");

        for (int index = 0; index < projects.size(); index++) {

            Project project = projects.get(index);
            System.out.println( ". " + project.getNombre());
        }
        System.out.println("\n");

        //Seleccionar producto para el que se calculará el envío.
                //Leer salto de línea para que pueda pregunta por el código del país. (https://stackoverflow.com/a/13102066/1601530)
        scanner.nextLine();
        System.out.println("Código del Formato para crear el  reporte ");
        System.out.println("(html, json, pdf):");
        String formatCode = scanner.nextLine();

        //Creamos el objeto que será pasado a la capa de dominio para que se haga el cálculo.
        Report reportEntity = new Report(projects,  formatCode);

        try {

            String formato = reportService.calculateDeliveryCost(reportEntity);
            System.out.println("El reporte se guardo en " + formato);
            System.out.println("\nPresiona Enter para continuar...");
            System.in.read();

        } catch (Exception exception) {
            System.out.println("No fue posible crear el reporte. " + exception.getMessage());
        }



    }
}
