package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT user.* FROM Users user\n" +
            "INNER JOIN Candidate candidate ON user.id = candidate.user_id\n" +
            "INNER JOIN Company company ON candidate.company_id = company.id\n" +
            "WHERE company.id = :companyId", nativeQuery = true)
    List<User> findByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT user.* FROM Users user\n" +
            "INNER JOIN Candidate candidate ON user.id = candidate.user_id\n" +
            "INNER JOIN Acceleration acceleration ON candidate.acceleration_id = acceleration.id\n" +
            "WHERE acceleration.name = :accelerationName", nativeQuery = true)
    List<User> findByAccelerationName(@Param("accelerationName") String accelerationName);

}
