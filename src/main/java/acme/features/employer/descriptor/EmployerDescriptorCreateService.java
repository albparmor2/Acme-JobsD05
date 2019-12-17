
package acme.features.employer.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDescriptorCreateService implements AbstractCreateService<Employer, Descriptor> {

	@Autowired
	EmployerDescriptorRepository repository;


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Descriptor d;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findJobByJobId(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		d = this.repository.findDescriptorByJobId(jobId);
		result = d == null && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");

	}

	@Override
	public Descriptor instantiate(final Request<Descriptor> request) {
		Descriptor result;

		result = new Descriptor();
		Job job;
		job = this.repository.findJobByJobId(request.getModel().getInteger("jobId"));
		result.setJob(job);
		return result;
	}

	@Override
	public void validate(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Descriptor> request, final Descriptor entity) {

		this.repository.save(entity);
	}

}
