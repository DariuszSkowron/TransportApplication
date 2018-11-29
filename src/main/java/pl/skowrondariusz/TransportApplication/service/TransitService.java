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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransitService {

    private static final Logger LOG = LoggerFactory.getLogger(TransitService.class);


    @Autowired
   TransitRepository transitRepository;

    private static final String API_KEY = "AIzaSyBlJos2RY_SBYeQIKWQJdwEN_2VnJhRY-0";


//    @Autowired
//    public TransitService(TransitRepository transitRepository){
//        this.transitRepository = transitRepository;
//        transitRepository.save(new Transit("Warszawa", "Kraków",999l));
//    }

    public void save(Transit transitAttribute){
        transitRepository.save(transitAttribute);
    }

    public void calculateDistance(Transit transit) {
        GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey(API_KEY).build();
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext);

        DistanceMatrix result = request.origins(transit.getSourceAdress())
                .destinations(transit.getDestinationAdress())
                .mode(TravelMode.DRIVING)
                .units(Unit.METRIC)
                .awaitIgnoreError();

        Long distance = result.rows[0].elements[0].distance.inMeters / 1000;
        transit.setDistance(distance);
    }

    public List<Transit> findAll() {
        Iterable<Transit> all = transitRepository.findAll();
        List<Transit> transits = convertToList(all);
        return transits;
    }

    private List<Transit> convertToList(Iterable<Transit> all) {
        List<Transit> transits = new ArrayList<>();
        for (Transit transit : all) {
            transits.add(transit);
        }
        return transits;
    }

    public List<Transit> getTransits(LocalDate startDate, LocalDate endDate) {
        List<Transit> transits = transitRepository.find(startDate, endDate);
        for(int i = 0; i < transits.size(); i++) {
            System.out.println(transits.get(i).toString());
        }
        return transits;
    }

    public Optional<Transit> getTransit(Long id){
        return transitRepository.findById(id);
    }


    public void addTransit(Transit transit) {
        transitRepository.save(transit);
    }

    public void getDailyReport(LocalDate startDate  , LocalDate endDate) {
        double totalDistance = 0.0;
        double totalPrice = 0.0;
        List<Transit> transits = getTransits(startDate, endDate);
        for (Transit transit : transits) {
            if (transit.getDistance() != null && transit.getPrice() != null) {
                    totalDistance = totalDistance + transit.getDistance();
                    totalPrice = totalPrice + transit.getPrice();

                }
            }
        reportsRepository.save()
    }

}
