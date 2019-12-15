
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.messageThread.Message;
import acme.entities.messageThread.Participation;
import acme.entities.messageThread.Thread;
import acme.features.antiSpamFilter.AntiSpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		int authenticatedId;
		int creatorId;
		int threadId;
		Principal principal;
		Participation p;

		principal = request.getPrincipal();
		authenticatedId = principal.getAccountId();
		threadId = request.getModel().getInteger("threadId");
		creatorId = this.repository.findUserIdByThreadId(threadId);
		p = this.repository.findParticipationByParticipantIdAndThreadId(authenticatedId, threadId);
		result = creatorId == authenticatedId || p != null;
		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirmMessage", "false");
		} else {
			request.transfer(model, "confirmMessage");
		}

		request.unbind(entity, model, "title", "tags", "body");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Thread t;
		Message result;
		Principal principal;
		Authenticated creator;

		result = new Message();
		t = this.repository.findThreadById(request.getModel().getInteger("threadId"));
		result.setThread(t);
		principal = request.getPrincipal();
		creator = this.repository.findAuthenticatedById(principal.getAccountId());
		result.setAuthenticated(creator);
		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean confirm = request.getModel().getBoolean("confirmMessage");
		errors.state(request, confirm, "confirmMessage", "acme.error.confirm");

		Customisation c;
		String spamWord;
		Double threshold;
		boolean isSpam;

		//Para poder usar los métodos de la clase
		AntiSpamFilter asf = new AntiSpamFilter();
		//Vamos a comprobar si el mensaje tiene spam
		c = this.repository.findCustomisation();
		spamWord = c.getSpamWord();
		threshold = c.getThreshold();
		String[] separateSpamWord = asf.separateSpamWord(spamWord);

		String title;
		if (!errors.hasErrors("title")) {
			title = entity.getTitle();
			isSpam = asf.isSpam(separateSpamWord, title, threshold);
			errors.state(request, isSpam == false, "title", "acme.spam.error.isSpam");
		}

		String tags;
		if (!errors.hasErrors("tags")) {
			tags = entity.getTags();
			isSpam = asf.isSpam(separateSpamWord, tags, threshold);
			errors.state(request, isSpam == false, "tags", "acme.spam.error.isSpam");
		}

		String body;
		if (!errors.hasErrors("body")) {
			body = entity.getBody();
			isSpam = asf.isSpam(separateSpamWord, body, threshold);
			errors.state(request, isSpam == false, "body", "acme.spam.error.isSpam");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
