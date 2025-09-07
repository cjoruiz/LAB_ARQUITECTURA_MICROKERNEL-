/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.microkernel.core.business;

import co.unicauca.microkerne.common.entities.Report;
import co.unicauca.microkerne.common.interfaces.IReportPlugin;
import co.unicauca.microkernel.core.plugin.manager.ReportPluginManager;

/**
 *
 * @author crist
 */
public class ReportService {
    public String calculateDeliveryCost(Report reportData) throws Exception {

        String formatCode = reportData.getFormatCode();
        ReportPluginManager manager = ReportPluginManager.getInstance();
        IReportPlugin plugin = manager.getReportPlugin(formatCode);

        if (plugin == null) {
            throw new Exception("No hay un plugin disponible para el formato indicado: " + formatCode);
        }

        return plugin.generateReport(reportData.getProjects());

    }
}
