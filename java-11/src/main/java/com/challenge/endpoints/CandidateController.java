package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/candidate")
@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping(value = "/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(
            @PathVariable("userId") Long userId,
            @PathVariable("companyId") Long companyId,
            @PathVariable("accelerationId") Long accelerationId)
    {
        return new ResponseEntity<CandidateDTO>(
                candidateMapper.map(
                        candidateService.findById(userId, companyId, accelerationId)
                        .orElseThrow(() -> new ResourceNotFoundException("Candidate"))),
                HttpStatus.OK
        );
    }

    @GetMapping(params = "companyId")
    public ResponseEntity<List<CandidateDTO>> findByCompanyId(
            @RequestParam Long companyId)
    {
        return new ResponseEntity<List<CandidateDTO>>(
                candidateMapper.map(
                        candidateService.findByCompanyId(companyId)),
                HttpStatus.OK);
    }

    @GetMapping(params = "accelerationId")
    public ResponseEntity<List<CandidateDTO>> findByAccelerationId(
            @RequestParam Long accelerationId)
    {
        return new ResponseEntity<List<CandidateDTO>>(
                candidateMapper.map(
                        candidateService.findByAccelerationId(accelerationId)),
                HttpStatus.OK);
    }
}
