package pl.skowrondariusz.TransportApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.model.Test;
import pl.skowrondariusz.TransportApplication.repository.TestRepository;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public void addTest(Test test){
        testRepository.save(test);
    }
}
