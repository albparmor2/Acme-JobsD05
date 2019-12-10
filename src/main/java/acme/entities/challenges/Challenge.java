
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				bronzeReward;

	@Column(length = 1024)
	@NotBlank
	private String				bronzeDescription;

	@NotBlank
	private String				silverReward;

	@Column(length = 1024)
	@NotBlank
	private String				silverDescription;

	@NotBlank
	private String				goldReward;

	@Column(length = 1024)
	@NotBlank
	private String				goldDescription;

}
