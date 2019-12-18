
package acme.features.employer.job;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
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
	public Job instantiate(final Request<Job> request) {
		Job result;

		result = new Job();
		Employer creator;

		creator = this.repository.findEmployerById(request.getPrincipal().getAccountId());
		result.setStatus(Status.Draft);
		result.setEmployer(creator);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Job j;
		Boolean correctFutureDate;
		Calendar calendar;

		if (!errors.hasErrors("reference")) {
			j = this.repository.existJob(entity.getReference());
			errors.state(request, j == null, "reference", "employer.job.form.error.existJob");
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
	public void create(final Request<Job> request, final Job entity) {

		this.repository.save(entity);
	}

}
