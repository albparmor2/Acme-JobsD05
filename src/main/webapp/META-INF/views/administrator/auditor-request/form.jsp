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
<acme:form-textbox code="administrator.auditor-request.form.label.authenticated.userAccount.username" path="authenticated.userAccount.username" readonly="true"/>
<acme:form-textbox code="administrator.auditor-request.form.label.status" path="status" readonly="true"/>
	<acme:form-textbox code="administrator.auditor-request.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textbox code="administrator.auditor-request.form.label.responsibilityStatement" path="responsibilityStatement" readonly="true"/>
	
	<acme:form-submit  test="${status == 'Pending'}" code="administrator.auditor-request.form.button.accept" action="/administrator/auditor-request/update-accepted"/>
	<acme:form-submit  test="${status == 'Pending'}" code="administrator.auditor-request.form.button.reject" action="/administrator/auditor-request/update-rejected"/>
	<acme:form-return code="administrator.auditor-request.form.button.return"/>
</acme:form>
