package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/acceleration")
@RestController
public class AccelerationController {

    @Autowired
    private AccelerationService accelerationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Acceleration> findById(
            @PathVariable("id") Long accelerationId)
    {
        return new ResponseEntity<Acceleration>(
                accelerationService.findById(accelerationId)
                .orElseThrow(() -> new ResourceNotFoundException("Acceleration")),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(
            @PathParam("company") Long companyId)
    {
        return new ResponseEntity<List<Acceleration>>(
                accelerationService.findByCompanyId(companyId),
                HttpStatus.OK
        );
    }
}
