
package acme.features.administrator.auditorRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditor-request/")
public class AdministratorAuditorRequestController extends AbstractController<Administrator, AuditorRequest> {

	@Autowired
	private AdministratorAuditorRequestShowService				showService;

	@Autowired
	private AdministratorAuditorRequestListService				listService;

	@Autowired
	private AdministratorAuditorRequestUpdateAcceptedService	updateAcceptedService;

	@Autowired
	private AdministratorAuditorRequestUpdateRejectedService	updateRejectedService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.UPDATE_ACCEPTED, BasicCommand.UPDATE, this.updateAcceptedService);
		super.addCustomCommand(CustomCommand.UPDATE_REJECTED, BasicCommand.UPDATE, this.updateRejectedService);
	}
}
