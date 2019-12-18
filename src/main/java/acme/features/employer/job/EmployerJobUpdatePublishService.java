
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.features.antiSpamFilter.AntiSpamFilter;
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
		String description;
		Collection<Duty> duties;

		if (!errors.hasErrors("description")) {
			description = entity.getDescription();
			errors.state(request, description != "", "description", "employer.job.form.error.descriptor");
		}

		jobId = request.getModel().getInteger("id");
		duties = this.repository.findDutysByJobId(jobId);
		errors.state(request, !duties.isEmpty(), "*", "employer.job.form.error.duty");

		if (!duties.isEmpty()) {
			Double percentageTotal = 0.0;
			for (Duty duty : duties) {
				percentageTotal += duty.getPercentage();
			}
			errors.state(request, percentageTotal == 100.00, "*", "employer.job.form.error.duty");
		}

		Customisation c;
		String spamWords;
		Double threshold;
		boolean isSpam;

		//Para poder usar los métodos de la clase
		AntiSpamFilter asf = new AntiSpamFilter();
		//Vamos a comprobar si el trabajo tiene spam
		c = this.repository.findCustomisation();
		spamWords = c.getSpamWord();
		threshold = c.getThreshold();
		String[] separateSpamWord = asf.separateSpamWord(spamWords);

		if (!errors.hasErrors("description")) {
			description = entity.getDescription();
			if (description != null && description != "") {
				isSpam = asf.isSpam(separateSpamWord, description, threshold);
				errors.state(request, isSpam == false, "description", "acme.spam.error.isSpam");
			}
		}

		String title;
		if (!errors.hasErrors("title")) {
			title = entity.getTitle();
			isSpam = asf.isSpam(separateSpamWord, title, threshold);
			errors.state(request, isSpam == false, "title", "acme.spam.error.isSpam");
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
