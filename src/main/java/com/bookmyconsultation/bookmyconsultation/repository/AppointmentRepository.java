package com.bookmyconsultation.bookmyconsultation.repository;

import com.bookmyconsultation.bookmyconsultation.entity.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, String> {

    public List<Appointment> findByUserIdOrderByAppointmentDateDesc(String userId);

    public Appointment findByDoctorIdAndTimeSlotAndAppointmentDate(String doctorId, String timeSlot, String appointmentDate);

}