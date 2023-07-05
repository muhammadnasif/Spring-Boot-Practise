package com.cns.assignment.service;

import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.repository.ProjectRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{
    private final ProjectRepository projectRepository;
    private final ResourceLoader resourceLoader;

    public ReportServiceImpl(ProjectRepository projectRepository, ResourceLoader resourceLoader){
        this.projectRepository = projectRepository;
        this.resourceLoader = resourceLoader;
    }

    public String exportReport (Long id) throws IOException, JRException {

//        String path = "C:\\Users\\DELL\\Desktop\\jasper-cns-report\\report.pdf";
        Map<String , Object> parameters = new HashMap<>();
        String path = "\\jasper-report\\jasper_cns.pdf";
        String staticFolderPath = this.resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();


        File folder = new File(path + "\\jasper-report");
        if(!folder.exists()) {
            boolean created = folder.mkdir();
        }

        parameters.put("createdBy", "Muhammad Nasif Imtiaz");
    
        List<ProjectEntity> projects = this.projectRepository.findProjectsByOwnerId(id);

        File file = ResourceUtils.getFile("classpath:jasper_summary.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projects);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, staticFolderPath + path);

        return path;
    }
}
