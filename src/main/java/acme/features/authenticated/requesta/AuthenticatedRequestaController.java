
package acme.features.authenticated.requesta;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requestas.Requesta;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/requesta/")
public class AuthenticatedRequestaController extends AbstractController<Authenticated, Requesta> {

	@Autowired
	private AuthenticatedRequestaListService	listService;

	@Autowired
	private AuthenticatedRequestaShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
