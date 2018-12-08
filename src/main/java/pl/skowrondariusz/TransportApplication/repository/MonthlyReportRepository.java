package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;

public interface MonthlyReportRepository extends JpaRepository<MonthlyReport, Long> {

}
