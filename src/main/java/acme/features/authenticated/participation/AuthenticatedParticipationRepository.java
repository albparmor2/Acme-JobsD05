
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipationRepository extends AbstractRepository {

	@Query("Select p from Participation p where p.thread.id = ?1")
	Collection<Participation> findParticipationsByThreadId(int id);

	@Query("Select t from Thread t where t.id= ?1")
	Thread findThreadById(int id);

	@Query("Select p from Participation where p.thread.id = ?1 and p.participant.id = ?2")
	Participation findParticipationByThreadIdAndParticipantId(int threadId, int participantId);
}
