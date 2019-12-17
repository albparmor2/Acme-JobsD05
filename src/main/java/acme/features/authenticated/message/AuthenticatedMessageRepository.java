
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.entities.messageThread.Message;
import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select t.creator.userAccount.id from Thread t where t.id = ?1")
	Integer findUserIdByThreadId(int id);

	@Query("select m from Message m join m.thread t where t.id = ?1")
	Collection<Message> findMessagesByThreadId(int id);

	@Query("select p From Participation p where p.participant.userAccount.id = ?1 and p.thread.id = ?2")
	Participation findParticipationByParticipantIdAndThreadId(int participantId, int threadId);

	@Query("select m.thread from Message m where m.id = ?1")
	Thread findThreadByMessageId(int messageId);

	@Query("select t from Thread t where t.id = ?1")
	Thread findThreadById(int threadId);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select c from Customisation c")
	Customisation findCustomisation();
}
