
package acme.features.authenticated.threadMessages;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedThreadCreateService implements AbstractCreateService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public Thread instantiate(final Request<Thread> request) {
		Thread result;
		Principal principal;
		Authenticated creator;

		result = new Thread();
		principal = request.getPrincipal();
		creator = this.repository.findAuthenticatedById(principal.getAccountId());
		result.setCreator(creator);

		return result;
	}

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Thread> request, final Thread entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
