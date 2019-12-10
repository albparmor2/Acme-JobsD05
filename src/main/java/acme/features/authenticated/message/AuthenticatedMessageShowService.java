
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Message;
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
		int threadId;
		int authenticatedThreadId;
		Principal principal;
		Collection<Integer> usersId;

		principal = request.getPrincipal();
		authenticatedId = principal.getAccountId();
		threadId = this.repository.findThreadId(request.getModel().getInteger("id"));
		usersId = this.repository.findManyUsersId(threadId);
		authenticatedThreadId = this.repository.findUserIdByid(threadId);
		result = authenticatedThreadId == authenticatedId || usersId.contains(principal.getAccountId());
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "tags", "body");
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
