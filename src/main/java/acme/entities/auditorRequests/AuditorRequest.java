
package acme.entities.auditorRequests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.applications.Status;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditorRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firm;

	@Column(length = 1024)
	@NotBlank
	private String				responsibilityStatement;

	@NotNull
	private Status				status;

	@ManyToOne(optional = false)
	private Authenticated		authenticated;
}
