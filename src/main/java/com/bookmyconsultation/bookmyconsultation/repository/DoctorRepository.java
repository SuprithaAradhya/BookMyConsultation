package com.bookmyconsultation.bookmyconsultation.repository;

import com.bookmyconsultation.bookmyconsultation.entity.Doctor;
import com.bookmyconsultation.bookmyconsultation.enums.Speciality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String> {

    List<Doctor> findBySpecialityOrderByRatingDesc(Speciality speciality);

    List<Doctor> findAllByOrderByRatingDesc();
}