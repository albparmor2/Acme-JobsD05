
package acme.features.provider.requesta;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestas.Requesta;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestaRepository extends AbstractRepository {

	@Query("select a from Requesta a where a.id = ?1")
	Requesta findOneById(int id);

	@Query("select a from Requesta a")
	Collection<Requesta> findManyAll();

	@Query("select a from Requesta a where a.ticker = ?1")
	Requesta existRequesta(String ticker);
}
