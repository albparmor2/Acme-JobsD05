
package acme.features.authenticated.threadMessages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Principal principal;
		Collection<Integer> usersId;
		Thread thread;

		principal = request.getPrincipal();
		authenticatedId = principal.getAccountId();
		usersId = this.repository.findManyUsersId(request.getModel().getInteger("id"));
		thread = this.repository.findOneById(request.getModel().getInteger("id"));
		result = thread.getCreator().getUserAccount().getId() == authenticatedId || usersId.contains(principal.getAccountId());
		return result;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
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
