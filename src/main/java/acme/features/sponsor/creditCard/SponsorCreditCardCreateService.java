
package acme.features.sponsor.creditCard;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCreditCardCreateService implements AbstractCreateService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		CreditCard cc = this.repository.findCreditCardBySponsorId(request.getPrincipal().getAccountId());

		return cc == null;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "holder", "brand", "month", "year", "cvv");
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		CreditCard result;

		result = new CreditCard();
		result.setSponsor(this.repository.findSponsorById(request.getPrincipal().getAccountId()));
		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("year") && !errors.hasErrors("month")) {
			Calendar cal = new GregorianCalendar();
			errors.state(request, entity.expirationDate().after(cal.getTime()), "year", "sponsor.error.form.creditCard.date");
		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {

		this.repository.save(entity);
	}

}
