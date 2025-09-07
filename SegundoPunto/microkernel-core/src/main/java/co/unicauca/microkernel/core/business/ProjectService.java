/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.microkernel.core.business;

import co.unicauca.microkerne.common.entities.Project;
import co.unicauca.microkerne.common.interfaces.IReportPlugin;
import co.unicauca.microkernel.core.plugin.manager.ReportPluginManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crist
 */
public class ProjectService{
    public List<Project> getAll() {

        List<Project> projects = new ArrayList<>();

        /*En un escenario normal, los productos vendrían de la capa de acceso a datos.
         * Para este ejemplo, se crearán objetos de prueba directamente aquí.
         * */
        Project productOne = new Project(101, "Sistema de Detección de Plagas con IA", "15/05/2025","Ana Pérez", "Luis Gómez","Dr. Juan Torres","Investigación","Sistemas");
        Project productTwo = new Project(205,"Automatización de Riego por Sensores","20/06/2025","Sofía Rojas","null","Ing. Carlos Méndez","Práctica Profesional","Electrónica");
        Project productThree = new Project(310, "Robot de Limpieza Autónomo","30/07/2025","Jorge Arias","Diana Castro","Dr. Laura Vélez","Investigación","Automática<");

        projects.add(productOne);
        projects.add(productTwo);
        projects.add(productThree);

        return projects;
    }
}
