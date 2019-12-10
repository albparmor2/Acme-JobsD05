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
	<acme:form-textbox code="administrator.company-record.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company-record.form.label.CEO" path="CEO"/>
	<acme:form-textarea code="administrator.company-record.form.label.activities" path="activities"/>
	<acme:form-url code="administrator.company-record.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.company-record.form.label.contactPhone" path="contactPhone"/>
	<acme:form-textbox code="administrator.company-record.form.label.contactEmail" path="contactEmail"/>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox readonly="true" code="administrator.company-record.form.label.incorporated" path="incorporated"/>
	</jstl:if>
	<acme:form-textbox code="administrator.company-record.form.label.stars" path="stars"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.company-record.form.button.update" action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.company-record.form.button.delete" action="/administrator/company-record/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.company-record.form.button.create" action="/administrator/company-record/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.company-record.form.button.update" action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.company-record.form.button.delete" action="/administrator/company-record/delete"/>
	<acme:form-return code="administrator.company-record.form.button.return"/>
	
</acme:form>