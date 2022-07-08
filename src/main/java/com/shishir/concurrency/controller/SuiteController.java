package com.shishir.concurrency.controller;

import com.shishir.concurrency.entities.Suite;
import com.shishir.concurrency.repository.SuiteRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/suites")
public class SuiteController {
    @Autowired
    private SuiteRepository suiteRepository;

    @GetMapping
    public ResponseEntity<List<Suite>> getAvailableSuites(){
        return new ResponseEntity<>(suiteRepository.findAllByStatus("AVAILABLE"), HttpStatus.OK);
    }

    @PutMapping("/{id}/{user}")
    public ResponseEntity<Suite> bookSuite(@PathVariable(name = "id", required = true) Long id, @PathVariable(name = "user", required = true) String user){
        Optional<Suite> suiteOptional = suiteRepository.findById(id);
        if(suiteOptional.isPresent()){
            Suite suite = suiteOptional.get();
            suite.setStatus("BOOKED");
            suite.setBookedBy(user);

            return new ResponseEntity<>(suiteRepository.save(suite), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Suite(), HttpStatus.NOT_ACCEPTABLE);
    }
}
