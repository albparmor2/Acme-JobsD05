
package acme.entities.messageThread;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;

public class Participation {

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Thread			thread;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated	participant;

}
