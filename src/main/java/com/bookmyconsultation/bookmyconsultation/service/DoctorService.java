package com.bookmyconsultation.bookmyconsultation.service;

import com.bookmyconsultation.bookmyconsultation.entity.Address;
import com.bookmyconsultation.bookmyconsultation.entity.Doctor;
import com.bookmyconsultation.bookmyconsultation.enums.Speciality;
import com.bookmyconsultation.bookmyconsultation.exception.InvalidInputException;
import com.bookmyconsultation.bookmyconsultation.exception.ResourceUnAvailableException;
import com.bookmyconsultation.bookmyconsultation.model.TimeSlot;
import com.bookmyconsultation.bookmyconsultation.repository.AddressRepository;
import com.bookmyconsultation.bookmyconsultation.repository.AppointmentRepository;
import com.bookmyconsultation.bookmyconsultation.repository.DoctorRepository;
import com.bookmyconsultation.bookmyconsultation.util.ValidationUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class DoctorService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AddressRepository addressRepository;


    public Doctor register(Doctor doctor) throws InvalidInputException {
        ValidationUtils.validate(doctor);
        if (doctor.getAddress() == null)
            throw new InvalidInputException(Arrays.asList("Address"));
        doctor.setId(UUID.randomUUID().toString());
        if (doctor.getSpeciality() == null) {
            doctor.setSpeciality(Speciality.GENERAL_PHYSICIAN);
        }
        Address address = doctor.getAddress();
        address.setId(doctor.getId());
        doctor.setAddress(addressRepository.save(address));
        doctorRepository.save(doctor);
        return doctor;
    }


    public Doctor getDoctor(String id) {
        return Optional.ofNullable(doctorRepository.findById(id))
                .get()
                .orElseThrow(ResourceUnAvailableException::new);
    }

    public List<Doctor> getAllDoctorsWithFilters(String speciality) {

        if (speciality != null && !speciality.isEmpty()) {
            return doctorRepository.findBySpecialityOrderByRatingDesc(Speciality.valueOf(speciality));
        }
        return getActiveDoctorsSortedByRating();
    }

    @Cacheable(value = "doctorListByRating")
    private List<Doctor> getActiveDoctorsSortedByRating() {
        log.info("Fetching doctor list from the database");
        return doctorRepository.findAllByOrderByRatingDesc()
                .stream()
                .limit(20)
                .collect(Collectors.toList());
    }

    public TimeSlot getTimeSlots(String doctorId, String date) {

        TimeSlot timeSlot = new TimeSlot(doctorId, date);
        timeSlot.setTimeSlot(timeSlot.getTimeSlot()
                .stream()
                .filter(slot -> {
                    return appointmentRepository
                            .findByDoctorIdAndTimeSlotAndAppointmentDate(timeSlot.getDoctorId(), slot, timeSlot.getAvailableDate()) == null;

                })
                .collect(Collectors.toList()));

        return timeSlot;

    }
}
