package com.bookmyconsultation.bookmyconsultation.controller;

import com.bookmyconsultation.bookmyconsultation.entity.Rating;
import com.bookmyconsultation.bookmyconsultation.exception.InvalidInputException;
import com.bookmyconsultation.bookmyconsultation.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @PostMapping
    public ResponseEntity submitRatings(@RequestBody Rating rating) throws InvalidInputException {
        ratingsService.submitRatings(rating);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Rating> getAppointment(@PathVariable String appointmentId){
        return ResponseEntity.ok(ratingsService.getRatings(appointmentId));
    }

}

