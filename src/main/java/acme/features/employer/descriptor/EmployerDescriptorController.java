
package acme.features.employer.descriptor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/descriptor/")
public class EmployerDescriptorController extends AbstractController<Employer, Descriptor> {

	@Autowired
	private EmployerDescriptorCreateService	createService;

	@Autowired
	private EmployerDescriptorUpdateService	updateService;

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
