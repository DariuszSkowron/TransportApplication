package pl.skowrondariusz.TransportApplication.repository;


import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.springframework.stereotype.Repository;
import pl.skowrondariusz.TransportApplication.model.Transit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TransitRepository {
    private List<Transit> transits = new ArrayList<>();
    private static final String API_KEY = "AIzaSyBlJos2RY_SBYeQIKWQJdwEN_2VnJhRY-0";


    public void addTransit(Transit transit){
        transits.add(transit);
    }

    public void calculateDistance(Transit transit){
        GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey(API_KEY).build();
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext);

        DistanceMatrix result = request.origins(transit.getSourceAdress())
                .destinations(transit.getDestinationAdress())
                .mode(TravelMode.DRIVING)
                .units(Unit.METRIC)
                .awaitIgnoreError();

        Long distance = result.rows[0].elements[0].distance.inMeters;
        transit.setDistance(distance);
    }

}
