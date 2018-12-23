package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skowrondariusz.TransportApplication.model.Test;
import sun.rmi.runtime.Log;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
