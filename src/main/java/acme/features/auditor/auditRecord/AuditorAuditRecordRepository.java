
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1 and a.auditor.id = ?2")
	Collection<AuditRecord> findMyArByJobId(int jobId, int auditorRecordId);

	@Query("select a from AuditRecord a where a.job.id = ?1 and a.status='Published' and a.auditor.id != ?2")
	Collection<AuditRecord> findManyByJobId(int jobId, int auditorRecordId);

}
