
package acme.features.administrator.chart;

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
}
