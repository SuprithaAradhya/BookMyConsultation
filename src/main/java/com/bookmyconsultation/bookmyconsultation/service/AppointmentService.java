package com.bookmyconsultation.bookmyconsultation.service;

import com.bookmyconsultation.bookmyconsultation.entity.Appointment;
import com.bookmyconsultation.bookmyconsultation.exception.InvalidInputException;
import com.bookmyconsultation.bookmyconsultation.exception.ResourceUnAvailableException;
import com.bookmyconsultation.bookmyconsultation.exception.SlotUnavailableException;
import com.bookmyconsultation.bookmyconsultation.repository.AppointmentRepository;
import com.bookmyconsultation.bookmyconsultation.repository.UserRepository;
import com.bookmyconsultation.bookmyconsultation.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;


    public String appointment(Appointment appointment) throws SlotUnavailableException, InvalidInputException {
        ValidationUtils.validate(appointment);
        Appointment existingappointment = appointmentRepository.
                findByDoctorIdAndTimeSlotAndAppointmentDate(appointment.getDoctorId(), appointment.getTimeSlot(), appointment.getAppointmentDate());
        if(existingappointment!=null){
            throw new SlotUnavailableException();
        }
        appointmentRepository.save(appointment);
        return appointment.getAppointmentId();
    }


    public Appointment getAppointment(String appointmentId){
        return Optional.ofNullable(appointmentRepository.findById(appointmentId))
                .get()
                .orElseThrow(ResourceUnAvailableException::new);
    }

    public List<Appointment> getAppointmentsForUser(String userId) {
        return appointmentRepository.findByUserIdOrderByAppointmentDateDesc(userId);
    }
}

