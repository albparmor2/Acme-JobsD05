
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Sponsor;
import acme.features.antiSpamFilter.AntiSpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {

		boolean res;
		CommercialBanner cb = this.repository.findOneCommercialBannerById(request.getModel().getInteger("id"));
		res = cb.getSponsor().getUserAccount().getId() == request.getPrincipal().getAccountId();
		return res;
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
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner cb;

		cb = this.repository.findOneCommercialBannerById(request.getModel().getInteger("id"));
		return cb;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

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
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
