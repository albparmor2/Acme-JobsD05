
package acme.features.authenticated.threadMessages;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select t from Thread t where t.id = ?1")
	Thread findOneById(int id);

	@Query("select p.thread from Participation p where p.participant.id = ?1")
	Set<Thread> findManyByAuthenticatedId(int id);

	@Query("select t from Thread t where t.creator.id = ?1")
	Set<Thread> findManyByCreatorId(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select p from Participation p where p.participant.id = ?1 and p.thread.id = ?2")
	Participation findParticipationByParticipantIdAndThreadId(int participantId, int threadId);
}
