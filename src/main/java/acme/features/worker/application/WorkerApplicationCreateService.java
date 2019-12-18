
package acme.features.worker.application;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.Status;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean res = true;
		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneJobById(jobId);
		Calendar cal = new GregorianCalendar();
		if (job.getDeadline().before(cal.getTime()) || !job.isFinalMode()) {
			res = false;
		}

		return res;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "job.reference", "job.title", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;
		result = new Application();
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		int workerId = request.getPrincipal().getAccountId();
		Worker worker = this.repository.findOneWorkerById(workerId);
		result.setWorker(worker);

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneJobById(jobId);
		result.setJob(job);
		result.setStatus(Status.Pending);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Application a;

		if (!errors.hasErrors("reference")) {
			a = this.repository.existApplication(entity.getReference());
			errors.state(request, a == null, "reference", "worker.application.form.error.existApplication");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);

	}

}
