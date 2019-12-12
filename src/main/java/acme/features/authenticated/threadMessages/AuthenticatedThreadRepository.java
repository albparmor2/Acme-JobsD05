
package acme.features.authenticated.threadMessages;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select t from Thread t where t.id = ?1")
	Thread findOneById(int id);

	@Query("select distinct m.authenticated.userAccount.id from Message m where m.thread.id = ?1")
	Collection<Integer> findManyUsersId(int threadId);

	@Query("select distinct m.thread from Message m where m.authenticated.id = ?1")
	Set<Thread> findManyByAuthenticatedId(int id);

	@Query("select t from Thread t where t.creator.id = ?1")
	Set<Thread> findManyByCreatorId(int id);

	@Query("select distinct m.authenticated.userAccount.username from Message m where m.thread.id = ?1")
	List<String> findManyUsers(int threadId);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedById(int id);
}
