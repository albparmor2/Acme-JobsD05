
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

		Authenticated a;
		a = this.repository.findOneAuthenticatedByUsername(request.getModel().getString("username"));
		entity.setParticipant(a);
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

		result = new Participation();
		threadId = request.getModel().getInteger("threadId");
		t = this.repository.findThreadById(threadId);
		result.setThread(t);

		return result;
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated a;
		Participation p;
		Thread t;
		int threadId;

		if (!errors.hasErrors("username")) {
			a = this.repository.findOneAuthenticatedByUsername(entity.getUsername());
			errors.state(request, a != null, "username", "authenticated.participation.error.dontExistAuthenticated");
			if (a != null) {
				threadId = request.getModel().getInteger("threadId");
				t = this.repository.findThreadById(threadId);
				errors.state(request, !a.equals(t.getCreator()), "username", "authenticated.participation.error.creator");
				p = this.repository.findOneParticipationByParticipantIdAndThreadId(a.getId(), threadId);
				errors.state(request, p == null, "username", "authenticated.participation.error.isAlreadyParticipant");
				if (p == null) {
					entity.setParticipant(a);
				}
			}
		}

	}

	@Override
	public void create(final Request<Participation> request, final Participation entity) {

		this.repository.save(entity);
	}

}
