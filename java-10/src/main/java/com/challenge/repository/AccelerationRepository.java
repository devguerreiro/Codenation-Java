package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {

    Optional<Acceleration> findByName(String name);

    @Query(value = "SELECT acceleration.* FROM Acceleration acceleration\n" +
            "INNER JOIN Candidate candidate ON acceleration.id = candidate.acceleration_id\n" +
            "INNER JOIN Company company ON candidate.company_id = company.id\n" +
            "WHERE company.id = :companyId", nativeQuery = true)
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

}
