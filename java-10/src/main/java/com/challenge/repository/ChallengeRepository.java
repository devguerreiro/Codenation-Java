package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query(value = "SELECT challenge.* FROM Challenge challenge\n" +
            "INNER JOIN Acceleration acceleration ON challenge.id = acceleration.challenge_id\n" +
            "INNER JOIN Candidate candidate ON acceleration.id = candidate.acceleration_id\n" +
            "INNER JOIN Users user ON candidate.user_id = user.id\n" +
            "WHERE acceleration.id = :accelerationId AND\n" +
            "user.id = :userId", nativeQuery = true)
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId, @Param("userId") Long userId);
}
