package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(
            @PathVariable("id") Long userId)
    {
        return new ResponseEntity<User>(
                userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User")
        ), HttpStatus.OK);
    }

    @GetMapping(params = "accelerationName")
    public ResponseEntity<List<User>> findByAccelerationName(
            @RequestParam String accelerationName)
    {
        return new ResponseEntity<List<User>>(
                userService.findByAccelerationName(accelerationName),
                HttpStatus.OK);
    }

    @GetMapping(params = "companyId")
    public ResponseEntity<List<User>> findByCompanyId(
            @RequestParam Long companyId)
    {
        return new ResponseEntity<List<User>>(
                userService.findByCompanyId(companyId),
                HttpStatus.OK);
    }
}
