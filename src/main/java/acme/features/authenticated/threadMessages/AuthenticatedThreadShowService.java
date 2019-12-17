
package acme.features.authenticated.threadMessages;

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
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		boolean result;
		int authenticatedId;
		int participantId;
		int threadId;
		Principal principal;
		Thread thread;
		Participation p;

		principal = request.getPrincipal();
		authenticatedId = principal.getAccountId();
		participantId = principal.getActiveRoleId();
		threadId = request.getModel().getInteger("id");
		p = this.repository.findParticipationByParticipantIdAndThreadId(participantId, threadId);
		thread = this.repository.findOneById(threadId);
		result = thread.getCreator().getUserAccount().getId() == authenticatedId || p != null;
		return result;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "creator.userAccount.username");
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
