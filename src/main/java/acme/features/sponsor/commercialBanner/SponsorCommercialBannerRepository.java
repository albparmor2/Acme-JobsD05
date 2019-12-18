
package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.CreditCard;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select a from CommercialBanner a where a.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

	@Query("select a from CommercialBanner a where a.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int id);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findSponsorById(int id);

	@Query("select c from CreditCard c where c.sponsor.userAccount.id = ?1")
	CreditCard findCreditCardBySponsorId(int id);

	@Query("select cu from Customisation cu")
	Customisation findCustomisation();

}
