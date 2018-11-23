package pl.skowrondariusz.TransportApplication.service;


import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransitService {

    private static final Logger LOG = LoggerFactory.getLogger(TransitService.class);

    private TransitRepository transitRepository;

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
