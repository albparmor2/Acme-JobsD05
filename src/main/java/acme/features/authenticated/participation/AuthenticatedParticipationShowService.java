
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipationShowService implements AbstractShowService<Authenticated, Participation> {

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

		request.unbind(entity, model);
	}

	@Override
	public Participation findOne(final Request<Participation> request) {
		assert request != null;

		Participation result;
		int participantId;
		int threadId;

		participantId = request.getModel().getInteger("id");
		threadId = request.getModel().getInteger("threadId");
		result = this.repository.findParticipationByThreadIdAndParticipantId(threadId, participantId);

		return result;
	}

}
