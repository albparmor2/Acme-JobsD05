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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.bronzeReward" path="bronzeReward"/>
	<acme:form-textbox code="authenticated.challenge.form.label.bronzeDescription" path="bronzeDescription"/>
	<acme:form-textarea code="authenticated.challenge.form.label.silverReward" path="silverReward"/>
	<acme:form-textbox code="authenticated.challenge.form.label.silverDescription" path="silverDescription"/>
	<acme:form-textarea code="authenticated.challenge.form.label.goldReward" path="goldReward"/>
	<acme:form-textbox code="authenticated.challenge.form.label.goldDescription" path="goldDescription"/>
	
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>