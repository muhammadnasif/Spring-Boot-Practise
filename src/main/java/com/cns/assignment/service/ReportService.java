package com.cns.assignment.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ReportService {
    public String exportReport(Long id) throws FileNotFoundException, JRException;
}
