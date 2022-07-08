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

    @PutMapping("/{id}/{user}")
    public ResponseEntity<Suite> bookSuite(@PathVariable(name = "id", required = true) Long id, @PathVariable(name = "user", required = true) String user) {
        Suite suite = suiteService.bookSuite(id, user);
        if (null != suite) {
            return new ResponseEntity<>(suite, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Suite(), HttpStatus.NOT_ACCEPTABLE);
    }
}
