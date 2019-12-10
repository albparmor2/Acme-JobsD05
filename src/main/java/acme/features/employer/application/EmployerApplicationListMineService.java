
package acme.features.employer.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerApplicationListMineService implements AbstractListService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "job.reference");

	}

	@Override
	public List<Application> findMany(final Request<Application> request) {
		assert request != null;

		Collection<Job> jobs;
		List<Application> result = new ArrayList<>();
		Principal principal;

		principal = request.getPrincipal();
		jobs = this.repository.findManyJobsByEmployerId(principal.getActiveRoleId());
		for (Job j : jobs) {
			List<Application> a;
			a = this.repository.findManyByJobId(j.getId());
			result.addAll(a);
		}
		return result;
	}

}
