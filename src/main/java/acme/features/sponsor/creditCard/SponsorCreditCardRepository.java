
package acme.features.sponsor.creditCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select cd from CreditCard cd where cd.id = ?1")
	CreditCard findOneCreditCardById(int id);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findSponsorById(int id);

	@Query("select cd from CreditCard cd where cd.sponsor.userAccount.id = ?1")
	CreditCard findCreditCardBySponsorId(int id);

}
