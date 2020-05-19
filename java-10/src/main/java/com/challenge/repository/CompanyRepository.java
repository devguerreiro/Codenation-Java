package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT distinct company.* FROM Company company\n" +
            "INNER JOIN Candidate candidate ON company.id = candidate.company_id\n" +
            "INNER JOIN Acceleration acceleration ON candidate.acceleration_id = acceleration.id\n" +
            "WHERE acceleration.id = :accelerationId", nativeQuery = true)
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query(value = "SELECT company.* FROM Company company\n" +
            "INNER JOIN Candidate candidate ON company.id = candidate.company_id\n" +
            "INNER JOIN Users user ON candidate.user_id = user.id\n" +
            "WHERE user.id = :userId", nativeQuery = true)
    List<Company> findByUserId(@Param("userId") Long userId);
}
