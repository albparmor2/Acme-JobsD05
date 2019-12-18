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
	<acme:form-textbox code="authenticated.sponsor.form.label.company" path="company"/>
	<jstl:if test="${command != 'create' && creditCardNumber !=null && creditCardNumber != '' }">
	<acme:form-textarea code="authenticated.sponsor.form.label.creditCardNumber" path="creditCardNumber" readonly="true"/>
	<acme:form-textarea code="authenticated.sponsor.form.label.holder" path="holder" readonly="true"/>
	<acme:form-textarea code="authenticated.sponsor.form.label.brand" path="brand" readonly="true"/>
	<acme:form-textarea code="authenticated.sponsor.form.label.month" path="month" readonly="true"/>
	<acme:form-textarea code="authenticated.sponsor.form.label.year" path="year" readonly="true"/>
	<acme:form-textarea code="authenticated.sponsor.form.label.cvv" path="cvv" readonly="true"/>
	</jstl:if>
	<jstl:if test="${(creditCardNumber == '' || creditCardNumber == null) && command != 'create'}">
	<a href=/acme-jobs/sponsor/credit-card/create><acme:message code="sponsor.form.label.creditCard.create"/></a>
	</jstl:if>
	<jstl:if test="${creditCardNumber != null && creditCardNumber != ''}">
	<a href=/acme-jobs/sponsor/credit-card/update><acme:message code="sponsor.form.label.creditCard.update"/></a>
	</jstl:if>
	
	<p></p>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>
	<acme:form-return code="authenticated.sponsor.form.button.return"/>
</acme:form>
