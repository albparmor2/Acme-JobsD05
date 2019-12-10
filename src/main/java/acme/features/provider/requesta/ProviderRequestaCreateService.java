
package acme.features.provider.requesta;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestas.Requesta;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestaCreateService implements AbstractCreateService<Provider, Requesta> {

	@Autowired
	ProviderRequestaRepository repository;


	@Override
	public boolean authorise(final Request<Requesta> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Requesta> request, final Requesta entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Requesta> request, final Requesta entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirmRequesta", "false");
		} else {
			request.transfer(model, "confirmRequesta");
		}

		request.unbind(entity, model, "ticker", "title", "description", "deadline", "reward");
	}

	@Override
	public Requesta instantiate(final Request<Requesta> request) {
		Requesta result;

		result = new Requesta();

		return result;
	}

	@Override
	public void validate(final Request<Requesta> request, final Requesta entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Requesta r = null;
		Boolean correctFutureDate;
		Calendar calendar;

		if (!errors.hasErrors("ticker")) {
			r = this.repository.existRequesta(entity.getTicker());
			errors.state(request, r == null, "ticker", "provider.requesta.form.error.existRequest");
		}

		Boolean confirm = request.getModel().getBoolean("confirmRequesta");
		errors.state(request, confirm, "confirmRequesta", "acme.error.confirm");

		Money reward = entity.getReward();
		if (!errors.hasErrors("reward")) {
			errors.state(request, reward.getAmount() > 0, "reward", "acme.money.error.positive");
			errors.state(request, reward.getCurrency().equals("€") || reward.getCurrency().equals("EUR"), "reward", "acme.money.error.currency");
		}

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			correctFutureDate = entity.getDeadline().after(calendar.getTime());
			errors.state(request, correctFutureDate, "deadline", "acme.date.error.futureDate");
		}
	}

	@Override
	public void create(final Request<Requesta> request, final Requesta entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
