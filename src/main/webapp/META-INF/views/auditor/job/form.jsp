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
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="auditor.job.form.label.description" path="description"/>
	
	<a href=/acme-jobs/auditor/duty/list?id=${id}><acme:message code="auditor.job.form.label.duty"/><a/>
	<p></p>
	<a href=/acme-jobs/auditor/audit-record/list?id=${id}><acme:message code="auditor.job.form.label.auditRecord"/></a>
	<p></p>
	<a href=/acme-jobs/auditor/audit-record/create?jobId=${id}><acme:message code="auditor.audit-record.form.label.create"/></a>
	<p></p>
	
	<acme:form-return code="auditor.job.form.button.return"/>
	
</acme:form>