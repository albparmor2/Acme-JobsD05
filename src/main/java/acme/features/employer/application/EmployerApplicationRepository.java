
package acme.features.employer.application;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyJobsByEmployerId(int employerId);

	@Query("select a from Application a where a.job.id = ?1")
	List<Application> findManyByJobId(int jobId);
}
