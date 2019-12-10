
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select t.authenticated.userAccount.id from Thread t where t.id = ?1")
	Integer findUserIdByid(int id);

	@Query("select m from Message m join m.thread t where t.id = ?1")
	Collection<Message> findMessagesByThreadId(int id);

	@Query("select distinct m.authenticated.userAccount.id from Message m where m.thread.id = ?1")
	Collection<Integer> findManyUsersId(int threadId);

	@Query("select m.thread.id from Message m where m.id = ?1")
	Integer findThreadId(int messageId);
}
