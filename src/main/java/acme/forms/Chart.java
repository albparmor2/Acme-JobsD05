
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chart implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	Object[]					numberOfCompaniesGroupedBySector;
	Object[]					numberOfInvestorGroupedBySector;
	Object[]					ratioOfJobsGroupedByStatus;
	Object[]					ratioOfApplicationsGroupedByStatus;
	Object[]					numberOfRejectedApplicationsLastFourWeeks;
	Object[]					numberOfPendingApplicationsLastFourWeeks;
	Object[]					numberOfAcceptedApplicationsLastFourWeeks;
	String[]					allDatesBeforeFourWeeks;
}
