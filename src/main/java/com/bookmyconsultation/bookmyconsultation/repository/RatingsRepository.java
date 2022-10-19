package com.bookmyconsultation.bookmyconsultation.repository;

import com.bookmyconsultation.bookmyconsultation.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends CrudRepository<Rating, String> {

    public List<Rating> findByDoctorId(String doctorId);

    public Rating findTopByDoctorIdAndAppointmentId(String doctorId, String appointmentId);

    public Rating findByAppointmentId(String appointmentId);
}