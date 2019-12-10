
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	Integer						totalAnnouncements;
	Integer						totalCompanyRecords;
	Integer						totalInvestorRecords;
	Double						minRewardRequesta;
	Double						maxRewardRequesta;
	Double						averageRewardRequesta;
	Double						standardRewardRequesta;
	Double						minRewardOffer;
	Double						maxRewardOffer;
	Double						averageRewardOffer;
	Double						standardRewardOffer;
	Double						averageJobsEmployer;
	Double						averageApplicationEmployer;
	Double						averageApplicationWorker;

}
