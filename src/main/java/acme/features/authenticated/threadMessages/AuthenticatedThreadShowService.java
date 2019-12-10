
package acme.features.authenticated.threadMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		result = thread.getAuthenticated().getUserAccount().getId() == authenticatedId || usersId.contains(principal.getAccountId());
		return result;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "users");
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		List<String> usernames = new ArrayList<>();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		String users = "";
		usernames = this.repository.findManyUsers(id);
		for (Integer i = 0; i < usernames.size(); i++) {
			if (usernames.size() - 1 == i) {
				users = users + usernames.get(i);
			} else {
				users = users + usernames.get(i) + ", ";
			}
		}
		result.setUsers(users);

		return result;
	}

}
