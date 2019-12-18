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
	<acme:form-textbox code="sponsor.credit-card.form.label.creditCardNumber" path="creditCardNumber"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.holder" path="holder"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.brand" path="brand"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.month" path="month"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.year" path="year"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.cvv" path="cvv"/>
	
	
	<acme:form-submit test="${command == 'create'}" code="sponsor.credit-card.form.button.create"
	action="/sponsor/credit-card/create"/>
	<acme:form-submit test="${command == 'update'}" code="sponsor.credit-card.form.button.update"
	action="/sponsor/credit-card/update"/>
	<p></p>
	
	<acme:form-return code="sponsor.credit-card.form.button.return"/>
	
</acme:form>