package com.cns.assignment.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReportService {
    public String exportReport(Long id) throws IOException, JRException;
}
