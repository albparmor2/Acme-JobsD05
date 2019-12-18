
package acme.features.sponsor.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Sponsor;
import acme.features.antiSpamFilter.AntiSpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, NonCommercialBanner> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {

		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "jingle");

	}

	@Override
	public NonCommercialBanner instantiate(final Request<NonCommercialBanner> request) {
		assert request != null;

		NonCommercialBanner ncb = new NonCommercialBanner();
		ncb.setSponsor(this.repository.findSponsorById(request.getPrincipal().getAccountId()));
		return ncb;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
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
	public void create(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {

		this.repository.save(entity);

	}

}
