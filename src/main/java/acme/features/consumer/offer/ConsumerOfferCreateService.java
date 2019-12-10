
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirmOffer", "false");
		} else {
			request.transfer(model, "confirmOffer");
		}

		request.unbind(entity, model, "title", "deadline", "text", "minReward", "maxReward", "ticker");

	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean correctFutureDate;
		if (!errors.hasErrors("deadline")) {
			Calendar calendar = new GregorianCalendar();
			correctFutureDate = entity.getDeadline().after(calendar.getTime());
			errors.state(request, correctFutureDate, "deadline", "acme.date.error.futureDate");
		}

		Offer o = null;
		if (!errors.hasErrors("ticker")) {
			o = this.repository.existOffer(entity.getTicker());
			errors.state(request, o == null, "ticker", "consumer.offer.form.error.existOffer");
		}

		Boolean confirm = request.getModel().getBoolean("confirmOffer");
		errors.state(request, confirm, "confirmOffer", "acme.error.confirm");

		Money minR = entity.getMinReward();
		if (!errors.hasErrors("minReward")) {
			errors.state(request, minR.getAmount() > 0, "minReward", "acme.money.error.positive");
			errors.state(request, minR.getCurrency().equals("€") || minR.getCurrency().equals("EUR"), "minReward", "acme.money.error.currency");
		}
		Money maxR = entity.getMaxReward();
		if (!errors.hasErrors("maxReward")) {
			errors.state(request, maxR.getAmount() > 0, "maxReward", "acme.money.error.positive");
			errors.state(request, maxR.getCurrency().equals("€") || maxR.getCurrency().equals("EUR"), "maxReward", "acme.money.error.currency");
		}
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
