
package acme.entities.banners;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				expirationDate;

	@NotBlank
	@Pattern(regexp = "^\\d{3,4}$", message = "acme.cvv.error.pattern")
	private String				cvv;

}
