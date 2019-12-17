
package acme.entities.messageThread;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participation extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	/*
	 * Es una propiedad que se utilizará para el servicio de crear participations
	 * Esta propiedad será la que se rellenará para añadir a alguien al hilo
	 */
	@NotBlank
	private String				username;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Thread				thread;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		participant;

}
