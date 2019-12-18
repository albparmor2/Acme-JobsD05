
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Status;
import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRequestUpdateRejectedService implements AbstractUpdateService<Administrator, AuditorRequest> {

	@Autowired
	AdministratorAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		Boolean result = true;
		Integer id = request.getModel().getInteger("id");
		AuditorRequest ar = this.repository.findOneAuditorRequestById(id);
		if (ar.getStatus() != Status.Pending) {
			result = false;
		}

		return result;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorRequestById(id);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(Status.Rejected);
		this.repository.save(entity);
	}

}
