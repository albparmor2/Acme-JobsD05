
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select distinct ar.job from AuditRecord ar where ar.auditor.id = ?1")
	Collection<Job> findManyJobsByAuditorId(int activeRoleId);

	@Query("select distinct j from Job j where j.status = 'Published' and j.id not in (select ar.job.id from AuditRecord ar where ar.auditor.id = ?1)")
	Collection<Job> findDistintJobsByAuditorId(int activeRoleId);

}
