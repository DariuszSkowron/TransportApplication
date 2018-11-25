package pl.skowrondariusz.TransportApplication.repository;


import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.skowrondariusz.TransportApplication.model.Transit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface TransitRepository extends CrudRepository<Transit, Long> {


    List<Transit> findByDateBetween(Date startDate, Date endDate);
}