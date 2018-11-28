package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.skowrondariusz.TransportApplication.model.Reports;

public interface ReportsRepository extends JpaRepository<Reports, Long> {

}
