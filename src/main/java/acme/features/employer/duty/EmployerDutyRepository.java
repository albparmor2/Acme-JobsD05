
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id = (select d from Descriptor d where d.job.id = ?1)")
	Collection<Duty> findDutiesByJobId(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);

	@Query("select d.job from Descriptor d where d.id = (select a.descriptor.id from Duty a where a.id = ?1)")
	Job findJobByDutyId(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findJobByJobId(int id);

	@Query("Select d from Descriptor d where d.job.id = ?1")
	Descriptor findDescriptorByJobId(int id);
}
