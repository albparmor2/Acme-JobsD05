
package acme.entities.jobs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@Column(length = 1024)
	@NotBlank
	private String				description;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Job					job;

}
