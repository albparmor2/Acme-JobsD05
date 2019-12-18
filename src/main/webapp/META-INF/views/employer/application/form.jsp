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
	<jstl:if test="${command != 'update-rejected'}">
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true"/>
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'update-rejected'}">
	<acme:form-textbox code="employer.application.form.label.justification" path="justification"/>
	</jstl:if>
	<jstl:if test="${status == 'Rejected'}">
	<acme:form-textbox code="employer.application.form.label.justification" path="justification" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command != 'update-rejected'}">
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.job.reference" path="job.reference" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.job.title" path="job.title" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'Pending'}">
	<acme:form-submit code="employer.job.form.button.update.accepted" action="/employer/application/update-accepted"/>
	<p></p>
	<a href=/acme-jobs/employer/application/update-rejected?id=${id}><acme:message code="employer.application.form.label.reject"/></a>
	<p></p>
	</jstl:if>
	<jstl:if test="${command == 'update-rejected'}">
	<acme:form-submit code="employer.job.form.button.confirm" action="/employer/application/update-rejected"/>
	</jstl:if>
	<acme:form-return code="employer.application.form.button.return"/>
	
</acme:form>