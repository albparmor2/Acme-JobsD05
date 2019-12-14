
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.banners.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends UserRole {

	// Serialisation identifier -------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ---------------------------------------------
	@NotBlank
	private String				company;

	@Valid
	@OneToOne(optional = true)
	CreditCard					creditCard;

}
