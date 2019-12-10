
package acme.features.worker.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1 and a.status='Published'")
	Collection<AuditRecord> findManyByJobId(int jobId);
}
