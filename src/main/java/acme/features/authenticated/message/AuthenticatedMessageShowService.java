
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Message;
import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		int authenticatedId;
		int creatorId;
		int messageId;
		Principal principal;
		Participation p;
		Thread t;

		principal = request.getPrincipal();
		authenticatedId = principal.getAccountId();
		messageId = request.getModel().getInteger("id");
		t = this.repository.findThreadByMessageId(messageId);
		creatorId = this.repository.findUserIdByThreadId(t.getId());
		p = this.repository.findParticipationByParticipantIdAndThreadId(authenticatedId, t.getId());
		result = creatorId == authenticatedId || p != null;
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "tags", "body", "authenticated.userAccount.username");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
