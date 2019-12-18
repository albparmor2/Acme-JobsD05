
package acme.entities.jobs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Column(length = 1024)
	@NotBlank
	private String				description;

	@NotNull
	@Range(min = 0, max = 100)
	private Double				percentage;

	@NotNull
	@ManyToOne(optional = false)
	@Valid
	private Job					job;

}
