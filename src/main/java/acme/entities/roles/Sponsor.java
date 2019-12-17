
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

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

	private String				creditCardNumber;

	private String				holder;

	private String				brand;

	private Integer				month;

	private Integer				year;

	private String				cvv;

}
