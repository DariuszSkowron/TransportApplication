package pl.skowrondariusz.TransportApplication.repository;


import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.skowrondariusz.TransportApplication.model.Transit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface TransitRepository extends JpaRepository<Transit, Long> {
//crudrepository//

    @Query("SELECT r FROM Transit r WHERE r.date between :start_date and :end_date")
    List<Transit> find(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);
//
    @Query("SELECT r FROM Transit r WHERE r.date = :date")
    List<Transit> findMonthly(@Param("date") LocalDate date);

}