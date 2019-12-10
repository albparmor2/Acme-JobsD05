
package acme.features.administrator.customisation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationRepository extends AbstractRepository {

	@Query("select a from Customisation a where a.id = ?1")
	Customisation findOneById(int id);

	@Query("select a from Customisation a")
	Collection<Customisation> findManyAll();
}
