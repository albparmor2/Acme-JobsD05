
package acme.entities.jobs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline"), @Index(columnList = "status")
})

public class Job extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	private String				reference;

	@NotNull
	private Status				status;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotNull
	@Valid
	private Money				salary;

	@URL
	private String				moreInfo;

	@Column(length = 1024)
	private String				description;


	@Transient
	public boolean isFinalMode() {
		boolean res = false;
		if (this.status == Status.Published) {
			res = true;
		}
		return res;
	};


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Employer employer;
}
