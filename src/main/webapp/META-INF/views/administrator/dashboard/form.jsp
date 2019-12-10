<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.dashboard.form.label.totalAnnouncements" path="totalAnnouncements"/>
	<acme:form-textbox code="administrator.dashboard.form.label.totalCompanyRecords" path="totalCompanyRecords"/>
	<acme:form-textbox code="administrator.dashboard.form.label.totalInvestorRecords" path="totalInvestorRecords"/>
	<acme:form-textbox code="administrator.dashboard.form.label.minRewardRequesta" path="minRewardRequesta"/>
	<acme:form-textbox code="administrator.dashboard.form.label.maxRewardRequesta" path="maxRewardRequesta"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageRewardRequesta" path="averageRewardRequesta"/>
	<acme:form-textbox code="administrator.dashboard.form.label.standardRewardRequesta" path="standardRewardRequesta"/>
	<acme:form-textbox code="administrator.dashboard.form.label.minRewardOffer" path="minRewardOffer"/>
	<acme:form-textbox code="administrator.dashboard.form.label.maxRewardOffer" path="maxRewardOffer"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageRewardOffer" path="averageRewardOffer"/>
	<acme:form-textbox code="administrator.dashboard.form.label.standardRewardOffer" path="standardRewardOffer"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageJobsEmployer" path="averageJobsEmployer"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageApplicationEmployer" path="averageApplicationEmployer"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageApplicationWorker" path="averageApplicationWorker"/>
	
	<acme:form-return code="administrator.announcement.form.button.return"/>
	
</acme:form>