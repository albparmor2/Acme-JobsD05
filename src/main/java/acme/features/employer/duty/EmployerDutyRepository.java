
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id = (select j.descriptor.id from Job j where j.id = ?1)")
	Collection<Duty> findDutiesByJobId(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);
}
