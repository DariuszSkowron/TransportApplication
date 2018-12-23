package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.skowrondariusz.TransportApplication.model.Reports;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {

}
