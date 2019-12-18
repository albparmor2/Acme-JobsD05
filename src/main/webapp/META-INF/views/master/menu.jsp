<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
		    <acme:menu-suboption code="master.menu.anonymous.albertoFavoriteLink" action="https://www.pokerstars.es/"/>
		    <acme:menu-suboption code="master.menu.anonymous.marcoFavoriteLink" action="http://weplan.appspot.com/"/>
		    <acme:menu-suboption code="master.menu.anonymous.emilioFavoriteLink" action="https://www.eurogamer.es/"/>
		    <acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.announcements" action="/anonymous/announcement/list"/>
			<acme:menu-suboption code="master.menu.anonymous.company-record" action="/anonymous/company-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.company-record-top" action="/anonymous/company-record-top/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investor-record" action="/anonymous/investor-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investor-record-top" action="/anonymous/investor-record-top/list"/>
		</acme:menu-option>

        <acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
            <acme:menu-suboption code="master.menu.authenticated.announcements" action="/authenticated/announcement/list"/>
            <acme:menu-suboption code="master.menu.authenticated.company-record" action="/authenticated/company-record/list"/>
            <acme:menu-suboption code="master.menu.authenticated.investor-record" action="/authenticated/investor-record/list"/>
            <acme:menu-suboption code="master.menu.authenticated.requestas" action="/authenticated/requesta/list"/>
            <acme:menu-suboption code="master.menu.authenticated.offers" action="/authenticated/offer/list"/>
            <acme:menu-suboption code="master.menu.authenticated.challenges" action="/authenticated/challenge/list"/>
            <acme:menu-suboption code="master.menu.authenticated.jobs" action="/authenticated/job/list"/>
            <acme:menu-suboption code="master.menu.authenticated.threads" action="/authenticated/thread/list-mine"/>
            <acme:menu-suboption code="master.menu.authenticated.thread.create" action="/authenticated/thread/create"/>
            
        </acme:menu-option>
        

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.company-record" action="/administrator/company-record/list"/>
    		<acme:menu-suboption code="master.menu.administrator.investor-record" action="/administrator/investor-record/list"/>	
      		<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.customisation" action="/administrator/customisation/list"/>
			<acme:menu-suboption code="master.menu.administrator.commercial-banner" action="/administrator/commercial-banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.non-commercial-banner" action="/administrator/non-commercial-banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.chart" action="/administrator/chart/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>
			<acme:menu-suboption code="master.menu.administrator.company-record.create" action="/administrator/company-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.investor-record.create" action="/administrator/investor-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			<acme:menu-suboption code="master.menu.administrator.non-commercial-banner.create" action="/administrator/non-commercial-banner/create"/>
			<acme:menu-suboption code="master.menu.administrator.commercial-banner.create" action="/administrator/commercial-banner/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.auditor-requests" action="/administrator/auditor-request/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.requesta.create" action="/provider/requesta/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
      <acme:menu-suboption code="master.menu.consumer.offer.create" action="/consumer/offer/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
		 <acme:menu-suboption code="master.menu.sponsor.commercial-banner" action="/sponsor/commercial-banner/list"/>
		 <acme:menu-suboption code="master.menu.sponsor.commercial-banner.create" action="/sponsor/commercial-banner/create"/>
		 <acme:menu-separator/>
         <acme:menu-suboption code="master.menu.sponsor.non-commercial-banner" action="/sponsor/non-commercial-banner/list"/>
         <acme:menu-suboption code="master.menu.sponsor.non-commercial-banner.create" action="/sponsor/non-commercial-banner/create"/>
         
        
		</acme:menu-option>

		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
      <acme:menu-suboption code="master.menu.auditor.jobs" action="/auditor/job/list-mine"/>
      <acme:menu-suboption code="master.menu.auditor.other-jobs" action="/auditor/job/list-not-mine"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
      <acme:menu-suboption code="master.menu.employer.job.list-mine" action="/employer/job/list-mine"/>
      <acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create"/>
      <acme:menu-suboption code="master.menu.employer.application.list-mine" action="/employer/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
      <acme:menu-suboption code="master.menu.worker.application.list-mine" action="/worker/application/list-mine"/>
      <acme:menu-suboption code="master.menu.worker.jobs" action="/worker/job/list"/>
		</acme:menu-option>
		

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor-request/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
      <acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create" access="!hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update" access="hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create" access="!hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')"/>

		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>