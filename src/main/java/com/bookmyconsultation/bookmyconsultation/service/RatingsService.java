package com.bookmyconsultation.bookmyconsultation.service;

import com.bookmyconsultation.bookmyconsultation.entity.Doctor;
import com.bookmyconsultation.bookmyconsultation.entity.Rating;
import com.bookmyconsultation.bookmyconsultation.exception.InvalidInputException;
import com.bookmyconsultation.bookmyconsultation.repository.DoctorRepository;
import com.bookmyconsultation.bookmyconsultation.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingsService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    //set a UUID for the rating
    //save the rating to the database
    //get the doctor id from the rating object
    //find that specific doctor with the using doctor id
    //modify the average rating for that specific doctor by including the new rating
    //save the doctor object to the database

    public void submitRatings(Rating rating) throws InvalidInputException {
        if(ratingsRepository.findTopByDoctorIdAndAppointmentId(rating.getDoctorId(), rating.getAppointmentId()) != null){
            throw new InvalidInputException(Arrays.asList("Already rated."));
        }
        rating.setId(UUID.randomUUID().toString());
        ratingsRepository.save(rating);
        Double averageRating = ratingsRepository
                .findByDoctorId(rating.getDoctorId())
                .stream()
                .collect(Collectors.averagingInt(Rating::getRating));
        Doctor doctor = doctorRepository.findById(rating.getDoctorId()).get();
        doctor.setRating(averageRating);
        doctorRepository.save(doctor);
    }

    public Rating getRatings(String appointmentId) {
        return ratingsRepository.findByAppointmentId(appointmentId);
    }

}

