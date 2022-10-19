package com.bookmyconsultation.bookmyconsultation.controller;

import com.bookmyconsultation.bookmyconsultation.entity.Appointment;
import com.bookmyconsultation.bookmyconsultation.exception.InvalidInputException;
import com.bookmyconsultation.bookmyconsultation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) throws InvalidInputException {
        return ResponseEntity.ok(appointmentService.appointment(appointment));
    }


    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable String appointmentId){
        return ResponseEntity.ok(appointmentService.getAppointment(appointmentId));
    }


}