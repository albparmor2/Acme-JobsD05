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
	
	<jstl:if test="${status=='Published'}">
	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status!='Published'}">
	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="auditor.audit-record.form.label.status" path="status" readonly="true"/>
	<acme:form-moment code="auditor.audit-record.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status=='Published'}">
	<acme:form-textbox code="auditor.audit-record.form.label.body" path="body" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status!='Published'}">
	<acme:form-textbox code="auditor.audit-record.form.label.body" path="body"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="auditor.audit-record.form.label.job.reference" path="job.reference" readonly="true"/>
	<acme:form-textbox code="auditor.audit-record.form.label.job.title" path="job.title" readonly="true"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show' && status=='Draft'}" code="auditor.audit-record.form.button.update"
	action="/auditor/audit-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="auditor.audit-record.form.button.delete"
	action="/auditor/audit-record/delete"/>
	<acme:form-submit test="${command == 'show' && status=='Draft'}" code="auditor.audit-record.form.button.update-publish"
	action="/auditor/audit-record/update-publish"/>
	<acme:form-submit test="${command == 'update'}" code="auditor.audit-record.form.button.update"
	action="/auditor/audit-record/update"/>
	<acme:form-submit test="${command == 'create'}" code="auditor.audit-record.form.button.create"
	action="/auditor/audit-record/create"/>
	<acme:form-submit test="${command == 'delete'}" code="auditor.audit-record.form.button.delete"
	action="/auditor/audit-record/delete"/>
	<acme:form-submit test="${command == 'update-publish' && status=='Draft'}" code="auditor.audit-record.form.button.update-publish"
	action="/auditor/audit-record/update-publish"/>
	
	<acme:form-return code="auditor.audit-record.form.button.return"/>
	
</acme:form>