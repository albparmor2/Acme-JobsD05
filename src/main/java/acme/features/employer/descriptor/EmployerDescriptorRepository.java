
package acme.features.employer.descriptor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDescriptorRepository extends AbstractRepository {

	@Query("Select j from Job j where j.id = ?1")
	Job findJobByJobId(int id);

	@Query("Select d from Descriptor d where d.job.id = ?1")
	Descriptor findDescriptorByJobId(int id);

}
