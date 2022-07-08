package com.shishir.concurrency.controller;

import com.shishir.concurrency.service.SuiteService;
import com.shishir.concurrency.entities.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/suites")
public class SuiteController {
    @Autowired
    private SuiteService suiteService;

    @GetMapping
    public ResponseEntity<List<Suite>> getAvailableSuites() {
        return new ResponseEntity<>(suiteService.findAllAvailableSuites(), HttpStatus.OK);
    }

    @PutMapping("/{id}/bookings")
    public ResponseEntity<Suite> bookSuite(@PathVariable(name = "id", required = true) Long id, @RequestBody SuiteBookingDto request) {
        Suite suite = new Suite();
        suite.setVersion(request.getVersion());
        suite.setBookedBy(request.getUser());
        Suite updatedSuite = suiteService.bookSuite(id, suite);
        if (null != updatedSuite) {
            return new ResponseEntity<>(updatedSuite, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Suite(), HttpStatus.NOT_ACCEPTABLE);
    }
}
