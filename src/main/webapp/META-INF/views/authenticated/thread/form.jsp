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
    <jstl:if test="${command != 'create'}">
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" readonly="true"/>
	<acme:form-moment code="authenticated.thread.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textbox code="authenticated.thread.form.label.creator.username" path="creator.userAccount.username" readonly="true"/>
	<a href="/acme-jobs/authenticated/participation/list?threadId=${id}"><acme:message code="authenticated.thread.form.label.participations"/></a>
	<p></p>
	<a href="/acme-jobs/authenticated/message/list?id=${id}"><acme:message code="authenticated.thread.form.label.messages"/></a>
	<p></p>
	<acme:form-submit code="authenticated.thread.form.button.create.participation" action="/authenticated/participation/create?threadId=${id}"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title"/>
	<acme:form-submit code="authenticated.thread.form.button.create"
	action="/authenticated/thread/create"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.thread.form.button.return"/>
	
	
</acme:form>