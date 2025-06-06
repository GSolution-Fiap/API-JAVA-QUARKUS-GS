package org.acme.bo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.ReportEntity;
import org.acme.service.ReportService;

import java.util.List;

@ApplicationScoped
public class ReportBO {

    @Inject
    private ReportService reportService;

    public List<ReportEntity> listAll() {
        return reportService.listAll();
    }

    public List<ReportEntity> getReportsByEmail(String email) {
        return reportService.getReportsByEmail(email);
    }

    @Transactional
    public ReportEntity createReport(ReportEntity entity) {
        return reportService.create(entity);
    }

    @Transactional
    public List<ReportEntity> updateReportsByEmail(String email, ReportEntity updatedData) {
        return reportService.updateByEmail(email, updatedData);
    }

    @Transactional
    public boolean deleteReportById(Integer id) {
        return reportService.deleteById(id);
    }
}
