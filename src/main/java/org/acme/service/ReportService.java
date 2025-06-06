package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.entity.ReportEntity;
import org.acme.repository.ReportRepository;

@ApplicationScoped
public class ReportService {

    @Inject
    private ReportRepository reportRepository;

    public List<ReportEntity> listAll() {
        return reportRepository.listAll();
    }

    public List<ReportEntity> getReportsByEmail(String email) {
        return reportRepository.findByEmail(email);
    }

    @Transactional
    public ReportEntity create(ReportEntity entity) {
        reportRepository.persist(entity);
        return entity;
    }

    @Transactional
    public List<ReportEntity> updateByEmail(String email, ReportEntity updatedData) {
        List<ReportEntity> reports = reportRepository.findByEmail(email);

        for (ReportEntity entity : reports) {
            entity.setName(updatedData.getName());
            entity.setCidade(updatedData.getCidade());
            entity.setLatitude(updatedData.getLatitude());
            entity.setLongitude(updatedData.getLongitude());
            entity.setNivelGravidade(updatedData.getNivelGravidade());
            entity.setEmail(updatedData.getEmail());

        }
        return reports;
    }


    @Transactional
    public boolean deleteById(Integer id) {
        ReportEntity entity = reportRepository.findById(id);
        if (entity == null) {
            return false;
        }
        reportRepository.delete(entity);
        return true;
    }
}
