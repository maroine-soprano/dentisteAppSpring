package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
