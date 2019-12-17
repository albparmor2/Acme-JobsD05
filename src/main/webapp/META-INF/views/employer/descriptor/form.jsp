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
	
	<input type="hidden" name="jobId" id="jobId" value="${param.jobId}"/>
    <acme:form-textarea code="employer.descriptor.form.label.description" path="description" />
	
	<acme:form-submit test="${command == 'create'}" code="employer.descriptor.form.button.create"
	action="/employer/descriptor/create"/>
	<acme:form-submit test="${command == 'update'}" code="employer.descriptor.form.button.update"
	action="/employer/descriptor/update"/>

	<acme:form-return code="employer.job.form.button.return"/>
		
</acme:form>

