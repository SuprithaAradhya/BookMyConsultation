package com.bookmyconsultation.bookmyconsultation.controller;

import com.bookmyconsultation.bookmyconsultation.entity.User;
import com.bookmyconsultation.bookmyconsultation.exception.InvalidInputException;
import com.bookmyconsultation.bookmyconsultation.service.AppointmentService;
import com.bookmyconsultation.bookmyconsultation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@RequestHeader("authorization") String accessToken,
                                        @PathVariable("id") final String userUuid) {
        final User User = userService.getUser(userUuid);
        return ResponseEntity.ok(User);
    }


    @PostMapping(path = "/register")
    public ResponseEntity<User> createUser(@RequestBody final User user) throws InvalidInputException {
        final User createdUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }


    @GetMapping("/{userId}/appointments")
    public ResponseEntity getAppointmentForUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUserDetails(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}