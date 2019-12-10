
package acme.features.authenticated.requesta;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestas.Requesta;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestaRepository extends AbstractRepository {

	@Query("select a from Requesta a where a.id = ?1")
	Requesta findOneById(int id);

	@Query("select a from Requesta a where a.deadline > current_timestamp")
	Collection<Requesta> findManyAll();
}
