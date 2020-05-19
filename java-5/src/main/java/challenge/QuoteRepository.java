package challenge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends CrudRepository<Script, Integer> {

    @Query(
            value = "SELECT * FROM Scripts\n" +
                    "ORDER BY RAND()\n" +
                    "LIMIT 1",
            nativeQuery = true)
    Optional<Script> getRandomScript();

    @Query(
            value = "SELECT * FROM\n" +
                        "(SELECT * FROM Scripts\n" +
                        "WHERE actor = :actorName)\n" +
                    "ORDER BY RAND()\n" +
                    "LIMIT 1",
            nativeQuery = true)
    Optional<Script> getRandomScriptByActorName(
            @Param("actorName") String actorName
    );
}
