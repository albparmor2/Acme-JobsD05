
package acme.entities.roles;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

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

	@CreditCardNumber
	private String				creditCardNumber;

	private String				holder;

	private String				brand;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				expirationDate;

	@Pattern(regexp = "^\\d{3,4}$", message = "acme.cvv.error.pattern")
	private String				cvv;

}
