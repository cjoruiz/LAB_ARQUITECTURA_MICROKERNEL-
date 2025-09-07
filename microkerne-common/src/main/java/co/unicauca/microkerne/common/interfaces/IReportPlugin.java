/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.microkerne.common.interfaces;

import co.unicauca.microkerne.common.entities.Project;
import java.util.List;

/**
 *
 * @author crist
 */
public interface IReportPlugin {
    String generateReport(List<Project> data);
}
