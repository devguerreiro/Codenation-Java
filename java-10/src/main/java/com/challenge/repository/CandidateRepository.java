package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, CandidateId> {

    @Query(value = "SELECT candidate.* FROM Candidate candidate\n" +
            "INNER JOIN Company company ON candidate.company_id = company.id\n" +
            "WHERE company.id = :companyId", nativeQuery = true)
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT candidate.* FROM Candidate candidate\n" +
            "INNER JOIN Acceleration acceleration ON candidate.acceleration_id = acceleration.id\n" +
            "WHERE acceleration.id = :accelerationId", nativeQuery = true)
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query(value = "SELECT candidate.* FROM Candidate candidate\n" +
            "WHERE candidate.id.user = :userId AND\n" +
            "candidate.id.company = :companyId AND\n" +
            "candidate.id.acceleration = :accelerationId", nativeQuery = true)
    Optional<Candidate> findByEachId(@Param("userId") Long userId, @Param("companyId") Long companyId, @Param("accelerationId") Long accelerationId);
}
