
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipationListService implements AbstractListService<Authenticated, Participation> {

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
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "participant.userAccount.username", "participant.userAccount.identity.name");
	}

	@Override
	public Collection<Participation> findMany(final Request<Participation> request) {
		assert request != null;

		int threadId;
		Collection<Participation> result;

		threadId = request.getModel().getInteger("threadId");
		result = this.repository.findParticipationsByThreadId(threadId);

		return result;
	}

}
