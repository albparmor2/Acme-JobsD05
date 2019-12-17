
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipationDeleteService implements AbstractDeleteService<Authenticated, Participation> {

	@Autowired
	AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		boolean result;
		int participationId;
		Participation p;
		Principal principal;

		participationId = request.getModel().getInteger("id");
		p = this.repository.findOneParticipationById(participationId);
		principal = request.getPrincipal();
		result = p.getThread().getCreator().getId() == principal.getActiveRoleId();
		return result;
	}

	@Override
	public void bind(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "participant.userAccount.username", "participant.userAccount.identity.name");
		request.unbind(entity, model, "participant.userAccount.identity.surname");
	}

	@Override
	public Participation findOne(final Request<Participation> request) {
		assert request != null;

		Participation result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneParticipationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Participation> request, final Participation entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
