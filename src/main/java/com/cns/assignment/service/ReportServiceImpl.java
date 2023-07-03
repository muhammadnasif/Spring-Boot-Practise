package com.cns.assignment.service;

import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.repository.ProjectRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{
    private final ProjectRepository projectRepository;

    public ReportServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public String exportReport (Long id) throws FileNotFoundException, JRException {

        String path = "C:\\Users\\DELL\\Desktop\\jasper-cns-report\\report.pdf";
        Map<String , Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Muhammad Nasif Imtiaz");

        List<ProjectEntity> projects = this.projectRepository.findAllById(Collections.singleton(id));

        File file = ResourceUtils.getFile("classpath:jasper_summary.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projects);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return path;
    }
}
