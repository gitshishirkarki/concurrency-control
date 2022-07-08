package com.shishir.concurrency.service;

import com.shishir.concurrency.entities.Suite;
import com.shishir.concurrency.repository.SuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SuiteService {
    @Autowired
    private SuiteRepository suiteRepository;

    public Optional<Suite> findOne(Long id, Integer version) {
        return suiteRepository.findByIdAndVersion(id, version);
    }

    public List<Suite> findAllAvailableSuites() {
        return suiteRepository.findAllByStatus("AVAILABLE");
    }

    @Transactional
    public Suite bookSuite(Long id, Suite suite) {
        Optional<Suite> suiteOptional = findOne(id, suite.getVersion());
        if (suiteOptional.isPresent()) {
            Suite availableSuite = suiteOptional.get();
            availableSuite.setStatus("BOOKED");
            availableSuite.setBookedBy(suite.getBookedBy());

            return suiteRepository.save(availableSuite);
        }
        return null;
    }

    @Transactional
    public Suite save(Suite suite){
        return suiteRepository.save(suite);
    }
}
