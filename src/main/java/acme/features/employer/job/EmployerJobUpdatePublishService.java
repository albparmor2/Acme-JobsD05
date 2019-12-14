
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdatePublishService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int jobId;
		Descriptor d;
		Collection<Duty> duties;

		jobId = request.getModel().getInteger("id");
		d = this.repository.findDescriptorByJobId(jobId);
		if (d != null) {
			duties = this.repository.findDutysByDescriptorId(d.getId());
			if (duties.isEmpty()) {
				errors.add("*", "employer.job.form.error.duty");
			} else {
				Double percentageTotal = 0.0;
				for (Duty duty : duties) {
					percentageTotal += duty.getPercentage();
				}
				if (percentageTotal != 100.00) {
					errors.add("*", "employer.job.form.error.duty");
				}
			}
		} else {
			errors.add("*", "employer.job.form.error.descriptor");
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(Status.Published);

		this.repository.save(entity);
	}

}
