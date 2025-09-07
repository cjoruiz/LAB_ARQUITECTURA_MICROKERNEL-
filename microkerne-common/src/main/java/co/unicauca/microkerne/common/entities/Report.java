/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.microkerne.common.entities;

import java.util.List;

/**
 *
 * @author crist
 */
public class Report {
    private List<Project> projects;
    private String formatCode;

    public Report(List<Project> projects, String formatCode) {
        this.projects = projects;
        this.formatCode = formatCode;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> project) {
        this.projects = project;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    
}
