
package acme.entities.banners;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;

	@NotNull
	@Min(value = 0)
	@Max(value = 12)
	private Integer				month;

	@NotNull
	private Integer				year;

	@NotBlank
	@Pattern(regexp = "^\\d{3,4}$", message = "acme.cvv.error.pattern")
	private String				cvv;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	Sponsor						sponsor;


	@Transient
	public Date expirationDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(this.year, this.month, 1);
		Date res = calendar.getTime();
		return res;
	}

}
