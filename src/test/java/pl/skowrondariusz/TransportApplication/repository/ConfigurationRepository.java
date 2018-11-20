package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.skowrondariusz.TransportApplication.model.Transit;

import javax.annotation.PostConstruct;

@Configuration
public class ConfigurationRepository {

    @Autowired
    private TransitRepository transitRepository;

    @PostConstruct
    public void setupTransitrs(){
        transitRepository.addTransit(new Transit("Poznan", "Warszawa", 120l, "2018-10-28"));
    }
}
