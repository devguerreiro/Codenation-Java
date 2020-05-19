package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query(value = "SELECT max(submission.score) FROM Submission submission\n" +
            "INNER JOIN Challenge challenge ON submission.challenge_id = challenge.id\n" +
            "WHERE challenge.id = :challengeId", nativeQuery = true)
    BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "SELECT submission.* FROM Submission submission\n" +
            "INNER JOIN Challenge challenge ON submission.challenge_id = challenge.id\n" +
            "INNER JOIN Acceleration acceleration ON challenge.id = acceleration.challenge_id\n" +
            "WHERE challenge.id = :challengeId AND\n" +
            "acceleration.id = :accelerationId", nativeQuery = true)
    List<Submission> findByChallegeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);
}
