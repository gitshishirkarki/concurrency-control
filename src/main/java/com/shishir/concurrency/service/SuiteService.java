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

    public Optional<Suite> findOne(Long id) {
        return suiteRepository.findById(id);
    }

    public List<Suite> findAllAvailableSuites() {
        return suiteRepository.findAllByStatus("AVAILABLE");
    }

    @Transactional
    public Suite bookSuite(Long id, String user) {
        Optional<Suite> suiteOptional = findOne(id);
        if (suiteOptional.isPresent()) {
            Suite suite = suiteOptional.get();
            suite.setStatus("BOOKED");
            suite.setBookedBy(user);

            return suiteRepository.save(suite);
        }
        return null;
    }
}
