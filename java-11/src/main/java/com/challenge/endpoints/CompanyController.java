package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> findById(
            @PathVariable("id") Long companyId)
    {
        return new ResponseEntity<Company>(
                companyService.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company")),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll(
            @PathParam("acceleration") Long accelerationId,
            @PathParam("user") Long userId)
    {
        if (accelerationId != null) {
            return new ResponseEntity<List<Company>>(
                    companyService.findByAccelerationId(accelerationId),
                    HttpStatus.OK
            );
        }

        if (userId != null) {
            return new ResponseEntity<List<Company>>(
                    companyService.findByUserId(userId),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<List<Company>>(
                companyService.findAll(),
                HttpStatus.OK
        );
    }
}
