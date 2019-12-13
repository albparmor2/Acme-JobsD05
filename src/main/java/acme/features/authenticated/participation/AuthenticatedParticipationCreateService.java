
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipationCreateService implements AbstractCreateService<Authenticated, Participation> {

	@Autowired
	AuthenticatedParticipationRepository repository;


	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		boolean result;
		Thread t;
		int threadId;
		Principal principal;

		threadId = request.getModel().getInteger("threadId");
		t = this.repository.findThreadById(threadId);
		principal = request.getPrincipal();
		result = t.getCreator().getId() == principal.getActiveRoleId();
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

		request.unbind(entity, model, "username");
	}

	@Override
	public Participation instantiate(final Request<Participation> request) {
		Participation result;
		Thread t;
		int threadId;
		Authenticated a;

		result = new Participation();
		threadId = request.getModel().getInteger("threadId");
		t = this.repository.findThreadById(threadId);
		result.setThread(t);
		a = new Authenticated();
		result.setParticipant(a);

		return result;
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated a;
		Participation p;
		int threadId;

		if (!errors.hasErrors("username")) {
			a = this.repository.findOneAuthenticatedByUsername(entity.getUsername());
			errors.state(request, a != null, "username", "Error de que no existe usuario");
			if (a != null) {
				threadId = request.getModel().getInteger("threadId");
				p = this.repository.findOneParticipationByParticipantIdAndThreadId(a.getId(), threadId);
				errors.state(request, p == null, "username", "Error de que ya es participante");
				if (p == null) {
					entity.setParticipant(a);
				}
			}
		}

	}

	@Override
	public void create(final Request<Participation> request, final Participation entity) {
		String username;
		Authenticated participant;

		username = entity.getUsername();
		participant = this.repository.findOneAuthenticatedByUsername(username);
		entity.setParticipant(participant);

		this.repository.save(entity);
	}

}
