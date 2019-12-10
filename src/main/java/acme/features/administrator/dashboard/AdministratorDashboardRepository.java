
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(*) FROM Announcement")
	Integer getTotalAnnouncements();

	@Query("SELECT count(*) FROM CompanyRecord")
	Integer getTotalCompanyRecords();

	@Query("SELECT count(*) FROM InvestorRecord")
	Integer getTotalInvestorRecords();

	@Query("SELECT count(*) FROM Requesta WHERE deadline>= ?1")
	Integer getActiveRequests(Date d);

	@Query("SELECT min(reward.amount) FROM Requesta WHERE deadline>= ?1")
	Double getMinRewardRequesta(Date d);

	@Query("SELECT max(reward.amount) FROM Requesta WHERE deadline>= ?1")
	Double getMaxRewardRequesta(Date d);

	@Query("SELECT avg(reward.amount) FROM Requesta WHERE deadline>= ?1")
	Double getAvgRewardRequesta(Date d);

	@Query("SELECT stddev(reward.amount) FROM Requesta WHERE deadline>= ?1")
	Double getStandardRewardRequesta(Date d);

	@Query("SELECT count(*) FROM Offer WHERE deadline>= ?1")
	Integer getActiveOfferts(Date d);

	@Query("SELECT min(minReward.amount) FROM Offer WHERE deadline>= ?1")
	Double getMinRewardOffer(Date d);

	@Query("SELECT max(maxReward.amount) FROM Offer WHERE deadline>= ?1")
	Double getMaxRewardOffer(Date d);

	@Query("SELECT avg((max_reward_amount + min_reward_amount)/2) FROM Offer WHERE deadline>= ?1")
	Double getAvgRewardOffer(Date d);

	@Query("SELECT stddev((max_reward_amount + min_reward_amount)/2) FROM Offer WHERE deadline>= ?1")
	Double getStandardRewardOffer(Date d);

	@Query("SELECT count(*) From Job")
	Double getJobs();

	@Query("SELECT count(*) From Application")
	Double getApplications();

	@Query("SELECT count(*) From Employer")
	Double getEmployers();

	@Query("SELECT count(*) From Worker")
	Double getWorkers();
}
