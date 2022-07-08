package com.shishir.concurrency.service;

import com.shishir.concurrency.entities.Suite;
import com.shishir.concurrency.repository.SuiteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SuiteServiceTest {
    @Autowired
    private SuiteRepository suiteRepository;

    @Test
    void bookSuite(){
        Suite suite = new Suite();
        suite.setStatus("AVAILABLE");
        suite.setName("Presidential Suite");
        suiteRepository.save(suite);

        List<Suite> suiteList = suiteRepository.findAllByStatus("AVAILABLE");

        Suite suiteBookFromBinod = suiteList.get(0);
        suiteBookFromBinod.setBookedBy("Binod");
        suiteBookFromBinod.setStatus("BOOKED");

        Suite suiteBookFromShishir = suiteList.get(0);
        suiteBookFromShishir.setBookedBy("Shishir");
        suiteBookFromShishir.setStatus("BOOKED");

        List<Suite> suites = new ArrayList<>();
        suites.add(suiteBookFromBinod);
        suites.add(suiteBookFromShishir);

        suiteRepository.saveAll(suites);
    }
}