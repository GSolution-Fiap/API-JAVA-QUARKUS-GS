package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.acme.entity.ReportEntity;

@ApplicationScoped
public class ReportRepository {

    @Inject
    private EntityManager em;

    @Transactional
    public void persist(ReportEntity report) {
        em.persist(report);
    }

    public List<ReportEntity> listAll() {
        TypedQuery<ReportEntity> query = em.createQuery(
                "SELECT r FROM ReportEntity r ORDER BY r.id", ReportEntity.class
        );
        return query.getResultList();
    }

    public ReportEntity findById(Integer id) {
        return em.find(ReportEntity.class, id);
    }

    public List<ReportEntity> findByEmail(String email) {
        TypedQuery<ReportEntity> query = em.createQuery(
                "SELECT r FROM ReportEntity r WHERE r.email = :email ORDER BY r.id", ReportEntity.class
        );
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Transactional
    public void delete(ReportEntity report) {
        if (!em.contains(report)) {
            report = em.merge(report);
        }
        em.remove(report);
    }
}
