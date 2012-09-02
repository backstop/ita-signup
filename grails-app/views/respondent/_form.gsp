<%@ page import="ita.signup.Respondent" %>



<div class="fieldcontain ${hasErrors(bean: respondentInstance, field: 'emailAddress', 'error')} required">
	<label for="emailAddress">
		<g:message code="respondent.emailAddress.label" default="Email Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="emailAddress" required="" value="${respondentInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: respondentInstance, field: 'response', 'error')} ">
	<label for="response">
		<g:message code="respondent.response.label" default="Response" />
		
	</label>
	<g:select name="response" from="${respondentInstance.constraints.response.inList}" value="${respondentInstance?.response}" valueMessagePrefix="respondent.response" noSelection="['': '']"/>
</div>

