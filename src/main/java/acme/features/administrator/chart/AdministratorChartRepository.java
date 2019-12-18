
package acme.features.administrator.chart;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChartRepository extends AbstractRepository {

	@Query("select c.sector,count(c) FROM CompanyRecord c group by c.sector order by c.sector")
	Object[] findCompanySector();

	@Query("select c.sector,count(c) FROM InvestorRecord c group by c.sector order by c.sector")
	Object[] findInvestorSector();

	@Query("select j.status,count(j) FROM Job j group by j.status order by j.status")
	Object[] findJobStatus();

	@Query("select a.status,count(a) FROM Application a group by a.status order by a.status")
	Object[] findApplicationStatus();

	@Query("select date(a.moment),count(a) FROM Application a where a.moment > ?1 and a.status = 0 group by day(a.moment)")
	Object[] findRejectedApplicationsLastFourWeeks(Date d);

	@Query("select date(a.moment),count(a) FROM Application a where a.moment > ?1 and a.status = 1 group by day(a.moment)")
	Object[] findPendingApplicationsLastFourWeeks(Date d);

	@Query("select date(a.moment),count(a) FROM Application a where a.moment > ?1 and a.status = 2 group by day(a.moment)")
	Object[] findAcceptedApplicationsLastFourWeeks(Date d);
}
