
package acme.features.employer.job;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

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
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary");
		request.unbind(entity, model, "moreInfo", "description");
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

		Boolean correctFutureDate;
		Calendar calendar;
		Job j;

		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);
		if (!job.getReference().equals(entity.getReference())) {
			if (!errors.hasErrors("reference")) {
				j = this.repository.existJob(entity.getReference());
				errors.state(request, j == null, "reference", "employer.job.form.error.existJob");
			}
		}

		Money salary = entity.getSalary();
		if (!errors.hasErrors("salary")) {
			errors.state(request, salary.getAmount() > 0, "salary", "acme.money.error.positive");
			errors.state(request, salary.getCurrency().equals("€") || salary.getCurrency().equals("EUR"), "salary", "acme.money.error.currency");
		}

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			correctFutureDate = entity.getDeadline().after(calendar.getTime());
			errors.state(request, correctFutureDate, "deadline", "acme.date.error.futureDate");
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
