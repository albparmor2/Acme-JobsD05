
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id = (select d from Descriptor d where d.job.id = ?1)")
	Collection<Duty> findDutiesByJobId(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);
}