
package acme.features.sponsor.commercialBanner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.CreditCard;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Sponsor;
import acme.features.antiSpamFilter.AntiSpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "creditCardNumber", "holder", "brand", "month", "year", "cvv");

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {

		CommercialBanner cb;
		cb = new CommercialBanner();
		cb.setSponsor(this.repository.findSponsorById(request.getPrincipal().getAccountId()));
		CreditCard c = this.repository.findCreditCardBySponsorId(request.getPrincipal().getAccountId());
		if (c != null) {
			cb.setCreditCardNumber(c.getCreditCardNumber());
			cb.setHolder(c.getHolder());
			cb.setBrand(c.getBrand());
			cb.setMonth(c.getMonth());
			cb.setYear(c.getYear());
			cb.setCvv(c.getCvv());
		}
		return cb;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CreditCard c = this.repository.findCreditCardBySponsorId(request.getPrincipal().getAccountId());
		errors.state(request, c != null, "*", "sponsor.error.form.commercial-banner.creditCard");
		Date d = c.expirationDate();
		Calendar cal = new GregorianCalendar();
		errors.state(request, d.after(cal.getTime()), "year", "sponsor.error.form.commercial-banner.creditCard.date");

		Customisation cu = this.repository.findCustomisation();
		String spamWords = cu.getSpamWord();
		Double threshold = cu.getThreshold();
		AntiSpamFilter asf = new AntiSpamFilter();
		String[] separateSpamWord = asf.separateSpamWord(spamWords);

		if (!errors.hasErrors("slogan")) {
			Boolean isSpam = asf.isSpam(separateSpamWord, entity.getSlogan(), threshold);
			errors.state(request, !isSpam, "slogan", "acme.spam.error.isSpam");

		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {

		this.repository.save(entity);
	}

}
