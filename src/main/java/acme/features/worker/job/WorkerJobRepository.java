
package acme.features.worker.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.status = 'Published' and j.deadline > ?1")
	Collection<Job> findActiveJobs(Date d);

	@Query("select d.description from Descriptor d where d.job.id = ?1")
	String findOneDescriptionOfDescriptorById(int id);
}
