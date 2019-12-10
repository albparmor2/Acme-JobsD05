
package acme.features.anonymous.companyRecordTop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/company-record-top/")
public class AnonymousCompanyRecordTopController extends AbstractController<Anonymous, CompanyRecord> {

	@Autowired
	private AnonymousCompanyRecordTopListService	listService;

	@Autowired
	private AnonymousCompanyRecordTopShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
