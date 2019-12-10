
package acme.features.administrator.dashboard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalAnnouncements", "totalCompanyRecords", "totalInvestorRecords", "minRewardRequesta", "maxRewardRequesta", "averageRewardRequesta", "standardRewardRequesta", "minRewardOffer", "maxRewardOffer",
			"averageRewardOffer", "standardRewardOffer", "averageJobsEmployer", "averageApplicationEmployer", "averageApplicationWorker");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard d = new Dashboard();
		Calendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Integer totalAnnouncements = this.repository.getTotalAnnouncements();
		d.setTotalAnnouncements(totalAnnouncements);
		Integer totalCompanyRecords = this.repository.getTotalCompanyRecords();
		d.setTotalCompanyRecords(totalCompanyRecords);
		Integer totalInvestorRecords = this.repository.getTotalInvestorRecords();
		d.setTotalInvestorRecords(totalInvestorRecords);

		Double minRewardRequesta = 0.0;
		Double maxRewardRequesta = 0.0;
		Double averageRewardRequesta = 0.0;
		Double standardRewardRequesta = 0.0;
		Integer requestas = this.repository.getActiveRequests(date);
		if (requestas != 0) {
			minRewardRequesta = this.repository.getMinRewardRequesta(date);
			maxRewardRequesta = this.repository.getMaxRewardRequesta(date);
			averageRewardRequesta = this.repository.getAvgRewardRequesta(date);
			standardRewardRequesta = this.repository.getStandardRewardRequesta(date);
		}
		d.setMinRewardRequesta(minRewardRequesta);
		d.setMaxRewardRequesta(maxRewardRequesta);
		d.setAverageRewardRequesta(averageRewardRequesta);
		d.setStandardRewardRequesta(standardRewardRequesta);

		Double minRewardOffer = 0.0;
		Double maxRewardOffer = 0.0;
		Double averageRewardOffer = 0.0;
		Double standardRewardOffer = 0.0;
		Integer offers = this.repository.getActiveOfferts(date);
		if (offers != 0) {
			minRewardOffer = this.repository.getMinRewardOffer(date);
			maxRewardOffer = this.repository.getMaxRewardOffer(date);
			averageRewardOffer = this.repository.getAvgRewardOffer(date);
			standardRewardOffer = this.repository.getStandardRewardOffer(date);
		}
		d.setMinRewardOffer(minRewardOffer);
		d.setMaxRewardOffer(maxRewardOffer);
		d.setAverageRewardOffer(averageRewardOffer);
		d.setStandardRewardOffer(standardRewardOffer);

		Double avgJobsEmployer = this.repository.getJobs() / this.repository.getEmployers();
		d.setAverageJobsEmployer(avgJobsEmployer);
		Double avgApplicationEmployer = this.repository.getApplications() / this.repository.getEmployers();
		d.setAverageApplicationEmployer(avgApplicationEmployer);
		Double avgApplicationWorker = this.repository.getApplications() / this.repository.getWorkers();
		d.setAverageApplicationWorker(avgApplicationWorker);

		return d;
	}

}
