package com.shishir.concurrency.service;

import com.shishir.concurrency.entities.Suite;
import com.shishir.concurrency.repository.SuiteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OptimisticLockingTest {
    @Autowired
    private SuiteRepository suiteRepository;
    @Autowired
    private SuiteService suiteService;

    @Test
    void bookSuite() {
            Suite suite = new Suite();
            suite.setStatus("AVAILABLE");
            suite.setName("Presidential Suite");
            suiteRepository.save(suite);

            List<Suite> suiteList = suiteRepository.findAllByStatus("AVAILABLE");

            Suite suiteBookFromBinod = suiteList.get(0);
            suiteBookFromBinod.setBookedBy("Binod");
            suiteBookFromBinod.setStatus("BOOKED");
            suiteBookFromBinod.setVersion(suiteList.get(0).getVersion());

            Suite suiteSavedForBinod = suiteService.bookSuite(suiteBookFromBinod.getId(), suiteBookFromBinod);

            Assertions.assertNotNull(suiteSavedForBinod);

            Suite suiteBookFromShishir = suiteList.get(0);
            suiteBookFromShishir.setBookedBy("Shishir");
            suiteBookFromShishir.setStatus("BOOKED");
            suiteBookFromBinod.setVersion(suiteList.get(0).getVersion());

            Suite suiteSavedForShishir = suiteService.bookSuite(suiteBookFromShishir.getId(), suiteBookFromShishir);
            Assertions.assertNull(suiteSavedForShishir);
        }
    }
